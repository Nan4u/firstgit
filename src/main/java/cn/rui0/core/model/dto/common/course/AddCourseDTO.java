package cn.rui0.core.model.dto.common.course;

import lombok.Data;

/**
 * Created by ruilin on 2018/12/15.
 */
@Data
public class AddCourseDTO {
    private String name;
    private String info;
    private Integer isOpen=1;
}
