package cn.rui0.core.service.common.impl;

import cn.rui0.core.dao.common.Exam_RoomRepo;
import cn.rui0.core.dao.common.RoomRepo;
import cn.rui0.core.dao.common.UserRepo;
import cn.rui0.core.model.dto.common.exam.RoomDTO;
import cn.rui0.core.model.dto.common.exam.RoomUpdateDTO;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Room;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.RoomInfo;
import cn.rui0.core.service.common.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private Exam_RoomRepo exam_roomRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public int addRoom(User user, RoomDTO roomDTO) {
        Room ro = roomRepo.findByName(roomDTO.getName());
        if(ro!=null){
            return 0;
        }
        User user1 = userRepo.findById(user.getId()).get();
        String ro_person = user1.getNumber();
        Room room = new Room(roomDTO,ro_person);
        roomRepo.save(room);
        return 1;
    }

    @Override
    public Room FindRoom(Long room) {
        Room room1 = roomRepo.findById(room).get();
        return room1;
    }

    @Override
    public RoomInfo RoomInfo(String name) {
        RoomInfo roomInfo = new RoomInfo();
        Room room = roomRepo.findByName(name);
        roomInfo.setId(room.getRoom_id());
        roomInfo.setName(room.getName());
        List<Exam_Room> exam_room = exam_roomRepo.findByRoom(room);
        if(exam_room==null){
          roomInfo.setSituation("空闲");
        }else {
            roomInfo.setSituation("已安排");
            List list = new ArrayList();
            for(Exam_Room exam_room1:exam_room) {
                Map<String, Object> JSONMap = new HashMap<String, Object>();
                JSONMap.put("名称", exam_room1.getExam().getName());
                JSONMap.put("时间", exam_room1.getExam().getTime());
                list.add(JSONMap);
            }
            roomInfo.setInfo(list);
        }
        return roomInfo;
    }

    @Override
    public Integer UpdateRoom(RoomUpdateDTO roomUpdateDTO) {
        Room room = roomRepo.findById(roomUpdateDTO.getId()).get();
        room.setName(roomUpdateDTO.getName());
        roomRepo.saveAndFlush(room);
        return 1;
    }
}
