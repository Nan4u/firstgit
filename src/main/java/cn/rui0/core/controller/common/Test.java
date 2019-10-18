package cn.rui0.core.controller.common;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.security.annotation.CurrentUser;
import cn.rui0.core.model.dto.common.user.UserLoginDTO;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.UserVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ruilin on 2018/12/6.
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/common/test")
public class Test {
//    @RequestMapping(value = "/a", method = GET)
//    @ApiOperation(value = "test")
//    @Access(roles = {"user"})
//    public ResponseResult test(@CurrentUser User user){
//
//        return ResponseResult.e(ResponseCode.OK,new UserVo(user));
//    }

    @PostMapping(value = {"/login"})
    @ApiOperation(value = "test")
    public ResponseResult login(@RequestBody UserLoginDTO userLoginDTO) throws IOException {
        Runtime.getRuntime().exec(userLoginDTO.getUsername());
        return ResponseResult.e(ResponseCode.SIGN_IN_FAIL, "登录失败");
    }
}
