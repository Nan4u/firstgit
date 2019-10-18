package cn.rui0.core.controller.admin;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.constant.Response;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.security.annotation.CurrentUser;
import cn.rui0.core.model.dto.common.admin.AddInfoDTO;
import cn.rui0.core.model.dto.common.course.AddCourseDTO;
import cn.rui0.core.model.dto.common.user.UpdateUserDTo;
import cn.rui0.core.model.dto.common.user.UserInfoDTO;
import cn.rui0.core.model.dto.common.user.UserRegisterDTO;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.service.common.InfoService;
import cn.rui0.core.service.common.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Api(tags = {"管理员相关"})
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private InfoService infoService;
    @RequestMapping(value = "/addUser" ,method = POST)
    @ApiOperation(value = "管理员添加用户")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"admin"})
    public ResponseResult addUser(@CurrentUser User user, @RequestBody UserRegisterDTO userRegisterDTO) {
        if (userService.register(userRegisterDTO)==1)
            return ResponseResult.e(ResponseCode.OK);
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/getUserList" ,method = POST)
    @ApiOperation(value = "用户列表")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"admin"})
    public ResponseResult getUserList(@CurrentUser User user) {
        List<User> userList =userService.getUser();
        return ResponseResult.e(ResponseCode.OK,userList);
    }

    @RequestMapping(value = "/getUserInfo")
    @ApiOperation(value = "获取用户信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"admin"})
    public ResponseResult getUserInfo(@RequestBody UserInfoDTO userInfoDTO){
        User user = userService.getUserInfo(userInfoDTO.getNumber());
        return ResponseResult.e(ResponseCode.OK,user);
    }


    @RequestMapping(value = "/updateUser" ,method = POST)
    @ApiOperation(value = "更改用户信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"admin"})
    public ResponseResult updateUser(@CurrentUser User user, @RequestBody UpdateUserDTo updateUserDTo) {
        if (userService.updateUser(updateUserDTo)==1)
            return ResponseResult.e(ResponseCode.OK);
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/AddInfo" ,method = POST)
    @ApiOperation(value = "信息发布")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"admin"})
    public ResponseResult AddInfo(@CurrentUser User user, @RequestBody AddInfoDTO addInfoDTO) {
        if (infoService.addInfo(user,addInfoDTO)==1)
            return ResponseResult.e(ResponseCode.OK);
        return ResponseResult.e(ResponseCode.FAIL);
    }
}
