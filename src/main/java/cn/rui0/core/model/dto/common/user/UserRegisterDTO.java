package cn.rui0.core.model.dto.common.user;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;

/**
 * Created by ruilin on 2018/12/6.
 */
@Data
public class UserRegisterDTO {
    @NotBlank(message = "用户名不能为空")
    @Pattern(regexp = "^(\\w){4,16}$",message = "用户名应为[A-Za-z0-9_]组成的4-16位字符！")
    private String username;
    private String sex;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(\\w){6,18}$",message = "密码应为[A-Za-z0-9_]组成的6-18位字符！")
    private String password;

    private String phone;

    private String birthday;

    private String identify;

    private String college;

    private String address;
}
