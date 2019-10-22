package cn.rui0.core.model.po.common;

import cn.rui0.common.util.Encrypt;
import cn.rui0.core.model.po.base.BaseEntity;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;

    private String name;

    private String sex;
    //@JSONField(serialize = false)
    private String pwd;

    private String phone;

    private String birthday;

    private String identify;

    private String college;

    private String address;

    private String number;

    private String imagePath;
    @JSONField(serialize = false)
    //管理员
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Info> infos;//文章列表
    @OneToMany(mappedBy = "user",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Stu_Exam> stu_exams;//文章列表
    @JSONField(serialize = false)
    @OneToMany(targetEntity = UserRole.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<UserRole> roleList=new ArrayList<>();
    public User() {
    }

    public User( String us_name, String sex,String us_pwd, String us_phone, String us_birthday, String us_identify, String us_college, String us_address, String number) {
        this.name = us_name;
        this.sex=sex;
        this.pwd = us_pwd;
        this.phone = us_phone;
        this.birthday = us_birthday;
        this.identify = us_identify;
        this.college = us_college;
        this.address = us_address;
        this.number = number;
    }
}