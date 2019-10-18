package cn.rui0.core.model.po.common;

import cn.rui0.core.model.po.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Stu_Exam extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;


    private String code;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "exam_room_id")
    private Exam_Room exam_room;

    private String number;

    private int hearing;

    private int reading;

    private int writing;

    public Stu_Exam(String code,Exam_Room ex_room,String se_number){
        this.code=code;
        this.exam_room=ex_room;
        this.number=se_number;
    }
}
