package cn.rui0.core.controller.common;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.security.annotation.CurrentUser;
import cn.rui0.common.util.JwtUtil;
import cn.rui0.core.model.dto.common.user.IdentifyDTO;
import cn.rui0.core.model.dto.common.user.QueryDTO;
import cn.rui0.core.model.dto.common.user.UserLoginDTO;
import cn.rui0.core.model.dto.common.user.UserRegisterDTO;
import cn.rui0.core.model.po.common.Info;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.IdentifyVo;
import cn.rui0.core.model.vo.common.InfoVo;
import cn.rui0.core.model.vo.common.ScoreVo;
import cn.rui0.core.model.vo.common.UserVo;
import cn.rui0.core.service.common.InfoService;
import cn.rui0.core.service.common.StudentService;
import cn.rui0.core.service.common.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.List;


/**
 * Created by ruilin on 2018/12/6.
 */
@RestController
@Api(tags = {"账户相关"})
@RequestMapping(value = "/common/user")
public class UserController {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private InfoService infoService;
    @Autowired
    private StudentService studentService;
    @PostMapping(value = "/identify")
    @ApiOperation(value = "身份证验证")
    public  ResponseResult identify(@RequestBody IdentifyDTO identifyDTO){
        IdentifyVo identifyVo = userService.identify(identifyDTO);
        return ResponseResult.e(ResponseCode.OK, identifyVo);
    }
    @PostMapping(value = "/register")
    @ApiOperation(value = "注册")
    public ResponseResult register(@RequestBody UserRegisterDTO userRegisterDTO){
            int status = userService.register(userRegisterDTO);
            if (status==1)
                return ResponseResult.e(ResponseCode.OK);
            return ResponseResult.e(ResponseCode.FAIL);
    }
    @PostMapping(value = "/query")
    @ApiOperation(value = "查询")
    public ResponseResult query(@RequestBody QueryDTO query){
        ScoreVo scoreVo = studentService.query(query.getNumber(),query.getName());
        if(scoreVo!=null)
        return ResponseResult.e(ResponseCode.OK, scoreVo);
        return ResponseResult.e(ResponseCode.FAIL);

    }
    @PostMapping(value = {"/login"})
    @ApiOperation(value = "登录")
    public ResponseResult login(@RequestBody UserLoginDTO userLoginDTO) {
        UserVo user = userService.checkUser(userLoginDTO.getUsername(), userLoginDTO.getPassword());
        if (user != null) {
            return ResponseResult.e(ResponseCode.SIGN_IN_OK, jwtUtil.creatToken(user.getName(), user.getId()).getToken());
        }

        return ResponseResult.e(ResponseCode.SIGN_IN_FAIL, "登录失败");
    }
    @PostMapping(value = {"/ShowInfo"})
    @ApiOperation(value = "信息展示")
    public ResponseResult ShowInfo() {
        List<InfoVo> info = infoService.ShowAll();
        return ResponseResult.e(ResponseCode.OK,info);
    }
//    @PostMapping(value = "/current")
//    @ApiOperation(value = "获取当前用户信息")
//    @Access(roles = {"admin","teacher","student"})
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
//    public ResponseResult current(@CurrentUser User user) {
//        if (user == null)
//            return ResponseResult.e(ResponseCode.NOT_SING_IN, "用户未登录");
//        return ResponseResult.e(ResponseCode.OK, new UserVo(user));
//    }


    @PostMapping(value = "/logout/{token}")
    @ApiOperation(value = "退出当前用户")
    @Access(roles = {"admin","teacher","student"})
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    public ResponseResult logout(@PathVariable String token) throws ServletException {
        jwtUtil.deleteToken(token);
        return ResponseResult.e(ResponseCode.OK, "退出成功");
    }

//    @PostMapping(value = "/rePassword")
//    @ApiOperation(value = "修改密码")
//    @Access(roles = {"admin","teacher","student"})
//    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
//    public ResponseResult current(@CurrentUser User user, @RequestBody RePasswordDTO rePasswordDTO) {
//        if (user == null)
//            return ResponseResult.e(ResponseCode.NOT_SING_IN, "用户未登录");
//        int status=userService.rePassword(user.getId(),rePasswordDTO.getOldPassword(),rePasswordDTO.getNewPassword());
//        if (status==1)
//            return ResponseResult.e(ResponseCode.OK);
//        return ResponseResult.e(ResponseCode.FAIL);
//    }
}
