package cn.rui0.core.model.po.common;

import cn.rui0.core.model.dto.common.exam.RoomDTO;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Room {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long room_id;

    private String name;

    private String person;

    @OneToMany(mappedBy = "room",cascade= CascadeType.ALL,fetch=FetchType.LAZY)
    private List<Exam_Room> exam_rooms;

    public Room(RoomDTO roomDTO,String ro_person){
        this.name=roomDTO.getName();
        this.person=ro_person;
    }
}
