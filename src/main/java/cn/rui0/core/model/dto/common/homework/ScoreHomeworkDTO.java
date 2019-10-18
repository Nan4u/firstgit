package cn.rui0.core.model.dto.common.homework;

import lombok.Data;

/**
 * Created by ruilin on 2018/12/15.
 */
@Data
public class ScoreHomeworkDTO {
    private Long studentId;
    private Long homeworkId;
    private String comment;
    private Double score;

}
