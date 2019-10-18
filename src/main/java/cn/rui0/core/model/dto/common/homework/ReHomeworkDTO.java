package cn.rui0.core.model.dto.common.homework;

import lombok.Data;

/**
 * Created by ruilin on 2018/12/15.
 */
@Data
public class ReHomeworkDTO {
    private Long homeworkId;
    private String title;
    private String content;
    private Integer isOpen=1;
}
