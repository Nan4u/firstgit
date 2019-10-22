package cn.rui0.core.model.po.common;

import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.po.base.BaseEntity;
import lombok.Data;
import org.apache.ibatis.annotations.One;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Exam extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long exam_id;

    private String name;

    private String time;

    private String content;

    private String stime;

    private String etime;

    private String number;

    private int  amount;
    @OneToMany(mappedBy = "exam",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Exam_Room>exam_rooms;

    public Exam(){

    }
    public Exam(ExamDTO examDTO,String number,int ex_amount){
        this.name=examDTO.getName();
        this.time=examDTO.getTime();
        this.content=examDTO.getContent();
        this.stime=examDTO.getStime();
        this.etime=examDTO.getEtime();
        this.number=number;
        this.amount=ex_amount;
    }
}
