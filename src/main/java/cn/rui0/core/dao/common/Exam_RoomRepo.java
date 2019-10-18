package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Exam_RoomRepo extends JpaRepository<Exam_Room,Long> {

    List<Exam_Room> findByRoom(Room room);
}
