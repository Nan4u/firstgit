package cn.rui0.core.model.dto.common.user;

import lombok.Data;

/**
 * Created by ruilin on 2018/12/18.
 */
@Data
public class RePasswordDTO {
    private String oldPassword;
    private String newPassword;
}
