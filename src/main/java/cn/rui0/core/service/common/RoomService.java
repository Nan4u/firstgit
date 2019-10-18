package cn.rui0.core.service.common;

import cn.rui0.core.model.dto.common.exam.RoomDTO;
import cn.rui0.core.model.dto.common.exam.RoomUpdateDTO;
import cn.rui0.core.model.po.common.Room;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.RoomInfo;

public interface RoomService {
    //【添加】 考场

    /**
     * 添加考场
     * @param user
     * @param roomDTO
     * @return
     */
    int addRoom(User user, RoomDTO roomDTO);
    /**
     * 考场信息
     * @param room
     * @return
     */
    Room FindRoom(Long room);

    /**
     * 考场详情
     * @param name
     * @return
     */
    RoomInfo RoomInfo(String name);
    /**
     * 考场修改
     * @param roomUpdateDTO
     * @return
     */
    Integer UpdateRoom(RoomUpdateDTO roomUpdateDTO);
}
