package cn.rui0.core.model.dto.common.exam;

import lombok.Data;

import java.util.List;


@Data
public class ExamDTO {
    private String name;
    private String time;
    private String content;
    private String stime;
    private String etime;
    private List room;
}
