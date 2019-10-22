package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Room;
import lombok.Data;

@Data
public class Exam_RoomVo {
    private long id;
    private int amount;
    private long  exam;
    private long room;

    public Exam_RoomVo(Exam_Room exam_room){
        this.id=exam_room.getExam_room_id();
        this.amount=exam_room.getAmount();
        this.exam=exam_room.getExam().getExam_id();
        this.room=exam_room.getRoom().getRoom_id();
    }
}
