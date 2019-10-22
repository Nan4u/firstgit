package cn.rui0.core.service.common.impl;

import cn.rui0.core.dao.common.ExamRepo;
import cn.rui0.core.dao.common.Exam_RoomRepo;
import cn.rui0.core.dao.common.Stu_ExamRepo;
import cn.rui0.core.dao.common.UserRepo;
import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.dto.common.exam.StuExamIdDTO;
import cn.rui0.core.model.po.common.*;
import cn.rui0.core.model.vo.common.ExamManageVo;
import cn.rui0.core.model.vo.common.ExamStudentVo;
import cn.rui0.core.service.common.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExamServiceImpl implements ExamService {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private Exam_RoomRepo exam_roomRepo;
    @Autowired
    private Stu_ExamRepo stu_examRepo;
    @Override
    public Exam addExam(ExamDTO examDTO) {
        String number=getNumber(examDTO);
        int ex_amount = examDTO.getRoom().size()*40;
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

    @Override
    public List<ExamManageVo> findInfo(String name) {
        List<Exam> list = new ArrayList<>();
        if(name.equals(""))
             list = examRepo.findAll();
        else {
            list = examRepo.findByNameLike("%"+name+"%");
        }
        List<ExamManageVo> list1 = new ArrayList<>();
        for(Exam exam:list){
            list1.add(new ExamManageVo(exam));
        }
        return list1;
    }

    @Override
    public List<ExamStudentVo> findStudent(String number) {
        long code = Long.parseLong(number);
        Exam  exam= examRepo.findById(code).get();
        List<Exam_Room> exam_room = exam_roomRepo.findByExam(exam);
        List <Stu_Exam> list = stu_examRepo.findByExamRoom(exam_room.get(0));
        List<ExamStudentVo> list1= new ArrayList<>();
        for(Stu_Exam stu_exam:list){
            list1.add(new ExamStudentVo(stu_exam));
        }
        return list1;
    }

    @Override
    public int updateScore(StuExamIdDTO stuExamIdDTO) {
        long number = Long.parseLong(stuExamIdDTO.getId());
        Stu_Exam stu_exam = stu_examRepo.findById(number).get();
        stu_exam.setReading(stuExamIdDTO.getReading());
        stu_exam.setHearing(stuExamIdDTO.getHearing());
        stu_exam.setWriting(stuExamIdDTO.getWriting());
        if((stuExamIdDTO.getReading()+stuExamIdDTO.getHearing()+stuExamIdDTO.getWriting())>=425)
            stu_exam.setIsPassed(true);
        else {
            stu_exam.setIsPassed(false);
        }
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
