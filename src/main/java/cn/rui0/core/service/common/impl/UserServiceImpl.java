package cn.rui0.core.service.common.impl;

import cn.rui0.common.util.Encrypt;
import cn.rui0.common.util.Identify;
import cn.rui0.common.util.JwtUtil;
import cn.rui0.core.dao.common.TotalRepo;
import cn.rui0.core.dao.common.UserRepo;
import cn.rui0.core.dao.common.UserRoleRepo;
import cn.rui0.core.model.dto.common.user.IdentifyDTO;
import cn.rui0.core.model.dto.common.user.UpdateUserDTo;
import cn.rui0.core.model.dto.common.user.UserRegisterDTO;
import cn.rui0.core.model.po.common.Total;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.po.common.UserRole;
import cn.rui0.core.model.vo.common.IdentifyVo;
import cn.rui0.core.model.vo.common.UserInfoVo;
import cn.rui0.core.model.vo.common.UserVo;
import cn.rui0.core.service.common.UserService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by ruilin on 2018/12/3.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserRoleRepo userRoleRepo;

    @Autowired
    private TotalRepo totalRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public UserVo checkUser(String username, String password) {
        User user=userRepo.findByName(username);
        if (user!=null&&user.getPwd().equals(Encrypt.md5(password)))
            return new UserVo(user);
        else
            return null;
    }
    @Override
    public int register(UserRegisterDTO userRegisterDTO) {
        //默认初始注册用户为学生
        if (userRepo.findByIdentify(userRegisterDTO.getIdentify())!=null)
            return 0;
        String us_power="student";
        int Default_Number= 20900000;
        Total total = totalRepo.findById(1L).get();
        int ID=total.getNumber();
        String number = String.valueOf(Default_Number + ID);
        total.setNumber(ID+1);
        User user=new User(userRegisterDTO.getUsername(),userRegisterDTO.getSex(),
                Encrypt.md5(userRegisterDTO.getPassword()),  userRegisterDTO.getPhone(),userRegisterDTO.getBirthday(),Encrypt.base64Decode(userRegisterDTO.getIdentify()),
                userRegisterDTO.getCollege(),userRegisterDTO.getAddress(),number);
        userRepo.saveAndFlush(user);
        UserRole userRole=new UserRole();
        userRole.setRole(us_power);
        userRole.setUser(user);
        userRoleRepo.save(userRole);
        totalRepo.save(total);
        return 1;
    }

    @Override
    public User findByUserId(Long id) {
        return userRepo.findById(id).orElse(null);
    }


    @Override
    public IdentifyVo identify(IdentifyDTO identifyDTO) {
        Identify identify = new Identify();
        IdentifyVo identifyVo = new IdentifyVo();
        String check = identify.Check(identifyDTO.getIdCard(),identifyDTO.getName());
        Map maps = (Map) JSON.parse(check);
       identifyVo.setMessage(maps.get("message").toString());
       if(maps.get("result")!=null) {
           Map maps2 = (Map)maps.get("result");
           identifyVo.setRes(maps2.get("res").toString());
           identifyVo.setCardID(Encrypt.base64Encode(identifyDTO.getIdCard()));
           identifyVo.setName(identifyDTO.getName());
       }
        return identifyVo;
    }

    @Override
    public List<UserInfoVo> getUser() {
        List<User> list = userRepo.findAll();
        List<UserInfoVo> list2 = new ArrayList<>();
        for(User user:list){
            list2.add(new UserInfoVo(user));
        }
        return list2;
    }

    @Override
    public int updateUser(UpdateUserDTo updateUserDTo) {
       String number = updateUserDTo.getNumber();
       User oldUser = userRepo.findByNumber(number);
       oldUser.setNumber(updateUserDTo.getNumber());
       oldUser.setName(updateUserDTo.getName());
       oldUser.setPwd(updateUserDTo.getPwd());
       oldUser.setPhone(updateUserDTo.getPhone());
       userRepo.saveAndFlush(oldUser);
       UserRole userRole = userRoleRepo.findByUser(oldUser);
       userRole.setRole(updateUserDTo.getPower());
       userRoleRepo.saveAndFlush(userRole);
       return 1;
    }

    @Override
    public User getUserInfo(String number) {
        User user = userRepo.findByNumber(number);
        return user;
    }
}
