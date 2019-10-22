package cn.rui0.core.model.vo.common;


import cn.rui0.core.model.po.common.Exam;
import lombok.Data;

@Data
public class ExamManageVo {
    private String number;
    private String name;
    private String time;
    public ExamManageVo(Exam exam){
        this.number=exam.getNumber();
        this.name=exam.getName();
        this.time=exam.getTime();
    }
}
