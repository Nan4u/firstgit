package cn.rui0.core.service.common;

import cn.rui0.core.model.dto.common.user.IdentifyDTO;
import cn.rui0.core.model.dto.common.user.UpdateUserDTo;
import cn.rui0.core.model.dto.common.user.UserRegisterDTO;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.IdentifyVo;
import cn.rui0.core.model.vo.common.UserInfoVo;
import cn.rui0.core.model.vo.common.UserVo;

import java.util.List;

/**
 * Created by Ruilin on 2018/4/19.
 */

public interface UserService {
    //通过用户名及密码核查用户登录
    UserVo checkUser(String username, String password);
    //增加用户
    int register(UserRegisterDTO userRegisterDTO);

    User findByUserId(Long id);


    IdentifyVo identify(IdentifyDTO identifyDTO);

    List<UserInfoVo> getUser();

    //修改用户信息
    int updateUser(UpdateUserDTo updateUserDTo);

    //获取用户信息
    User getUserInfo(String number);
}
