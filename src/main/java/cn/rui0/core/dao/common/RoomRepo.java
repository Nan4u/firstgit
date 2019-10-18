package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepo extends JpaRepository<Room,Long> {
    Room findByName(String name);
}
