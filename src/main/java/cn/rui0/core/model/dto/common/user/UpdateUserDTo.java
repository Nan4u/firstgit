package cn.rui0.core.model.dto.common.user;

import lombok.Data;

@Data
public class UpdateUserDTo {
    private String number;
    private String name;
    private String pwd;
    private String phone;
    private String power;
}
