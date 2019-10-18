package cn.rui0.core.model.vo.common;

import lombok.Data;

/**
 * Created by ruilin on 2018/12/18.
 */
@Data
public class StudentVo {
    private String username;

    private String email;

    private String phone;

    private String realName;

    private Integer isActive;

    private Long studentId;
}
