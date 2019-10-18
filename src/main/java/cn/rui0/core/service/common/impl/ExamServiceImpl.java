package cn.rui0.core.service.common.impl;

import cn.rui0.core.dao.common.ExamRepo;
import cn.rui0.core.dao.common.Exam_RoomRepo;
import cn.rui0.core.dao.common.UserRepo;
import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Room;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.service.common.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private Exam_RoomRepo exam_roomRepo;
    @Override
    public Exam addExam(ExamDTO examDTO) {
        String number=getNumber(examDTO);
        int ex_amount = examDTO.getRoom().size();
        Exam exam = new Exam(examDTO,number,ex_amount);
        examRepo.saveAndFlush(exam);
        return exam;
    }

    @Override
    public Integer addExam_Room(Exam exam, Room room) {
        Exam_Room  exam_room = new Exam_Room();
        exam_room.setExam(exam);
        exam_room.setRoom(room);
        exam_room.setAmount(0);
        exam_roomRepo.saveAndFlush(exam_room);
        return 1;
    }

    private String getNumber(ExamDTO examDTO){
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String a=df.format(day);
        a=a.replace("-","");
        String name=examDTO.getName();
        name =name.substring(14,15);
        String number =a+name;
        return number;
    }
}
