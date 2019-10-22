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

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "code")
    private User user;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "exam_room_id")
    private Exam_Room examRoom;

    private String number;

    private int hearing;

    private int reading;

    private int writing;

    private Boolean isPassed=false;
    public Stu_Exam(){

    }
    public Stu_Exam(String number,Exam_Room ex_room,User user){
        this.number=number;
        this.examRoom=ex_room;
        this.user=user;
    }
}
