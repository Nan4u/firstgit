package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.Stu_Exam;
import lombok.Data;

@Data
public class Stu_ExamVo {
    private  String id;
    private String name;
    private String time;
    private String score;
    private String isPassed;
    private String info;

    public Stu_ExamVo(Stu_Exam stu_exam,String isPassed,String info){
        this.id=stu_exam.getExamRoom().getExam().getNumber();
        this.name=stu_exam.getExamRoom().getExam().getName();
        this.time=stu_exam.getExamRoom().getExam().getTime();
        this.score=String.valueOf(stu_exam.getReading()+stu_exam.getWriting()+stu_exam.getHearing());
        this.isPassed=isPassed;
        this.info=info;
    }
}
