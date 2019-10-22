package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.Stu_Exam;
import lombok.Data;

@Data
public class ExamStudentVo {
    private String id;
    private  String number;
    private String name;
    private String score;
    private String hearing;
    private String reading;
    private String writing;

    public ExamStudentVo(Stu_Exam stu_exam){
        this.id = String.valueOf(stu_exam.getId());
        this.number=stu_exam.getUser().getNumber();
        this.name=stu_exam.getUser().getName();
        this.score=String.valueOf(stu_exam.getReading()+stu_exam.getWriting()+stu_exam.getHearing());
        this.hearing=String.valueOf(stu_exam.getHearing());
        this.reading=String.valueOf(stu_exam.getReading());
        this.writing=String.valueOf(stu_exam.getWriting());

    }
}
