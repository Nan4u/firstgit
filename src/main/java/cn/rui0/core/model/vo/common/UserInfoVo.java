package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.User;
import lombok.Data;

@Data
public class UserInfoVo {
    private long id;
    private String address;
    private String birthday;
    private String college;
    private String identify;
    private String name;
    private String imagePath;
    private String sex;

    public UserInfoVo(User user){
        this.id=user.getId();
        this.address=user.getAddress();
        this.birthday=user.getBirthday();
        this.college=user.getCollege();
        this.identify=user.getIdentify();
        this.name=user.getName();
        this.imagePath=user.getImagePath();
        this.sex=user.getSex();
    }
}
