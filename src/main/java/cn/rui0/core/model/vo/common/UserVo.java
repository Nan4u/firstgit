package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.Student;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.po.common.UserRole;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ruilin on 2018/11/28.
 */
@Data
public class UserVo {

    private Long id;

    private String name;

    private String phone;

    private String birthday;

    private String identify;

    private String college;

    private String address;

    private String number;

    private List<String> roleList=new ArrayList<>();

    public UserVo(User user) {
        this.id=user.getId();
        this.name = user.getName();
        this.phone = user.getPhone();
        this.birthday = user.getBirthday();
        this.identify=user.getIdentify();
        this.college = user.getCollege();
        this.address = user.getAddress();
        this.number = user.getNumber();
        for (UserRole roles:user.getRoleList()) {
            this.roleList.add(roles.getRole());
        }
    }

}
