package cn.rui0.core.model.po.common;

import cn.rui0.core.model.po.base.BaseEntity;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Exam_Room extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long exam_room_id;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "number")
    private Exam exam;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "room_id")
    private Room room;

    private int amount;

    @OneToMany(mappedBy = "exam_room",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Stu_Exam> Stu_Exam ;

}
