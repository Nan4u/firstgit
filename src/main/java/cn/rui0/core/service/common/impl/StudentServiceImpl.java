package cn.rui0.core.service.common.impl;

import cn.rui0.core.dao.common.*;
import cn.rui0.core.model.po.common.*;
import cn.rui0.core.model.vo.common.ScoreVo;
import cn.rui0.core.service.common.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.*;

import static cn.rui0.common.util.BaseFile.FOLDER;


/**
 * Created by ruilin on 2018/12/11.
 */
@Service
@Transactional
public class StudentServiceImpl implements StudentService{

    private static String USER_PATH = FOLDER + "user" + File.separator;
    private final static String USER_FILE_PATH = "user" + File.separator;

    @Autowired
    private ExamRepo examRepo;
    @Autowired
    private Stu_ExamRepo stu_examRepo;
    @Autowired
    private Exam_RoomRepo exam_roomRepo;
    @Autowired
    private UserRepo userRepo;
    @Override
    public Integer sign_up(String number,  Exam_Room exam_room) {
        String er_number="33037";
        String a = number.substring(4,8);
        String b = String.valueOf(exam_room.getRoom().getRoom_id());
        String c = String.valueOf(exam_room.getAmount());
        er_number=er_number+a+b+c;
        Stu_Exam stu_exam = new Stu_Exam(number,exam_room,er_number);
        stu_examRepo.saveAndFlush(stu_exam);
        return 1;
    }

    @Override
    public List<Exam> ShowExam(String name) {
        if(name.equals(""))
            return examRepo.findAll();
       return examRepo.findByName(name);
    }

    @Override
    public Exam_Room getRoom(String ex_number) {
        List<Exam_Room> list = exam_roomRepo.findAll();
        for(Exam_Room exam_room:list){
            if(exam_room.getExam().getNumber().equals(ex_number)&&exam_room.getAmount()<40) {
                return exam_room;
            }
        }
        return null;
    }

    @Override
    public User ShowInfo(User user) {
        User user1 = userRepo.findById(user.getId()).get();
        return user1;
    }

    @Override
    public User FindNumber(User user) {
        User user1 = userRepo.findById(user.getId()).get();
        return user1;
    }

    @Override
    public List<Stu_Exam> FindScore(String number, String year) {
        List<Stu_Exam> list = stu_examRepo.findByCode(number);
        List<Stu_Exam> list2 = new ArrayList<>();
        for(Stu_Exam stu_exam:list){
            String time=stu_exam.getExam_room().getExam().getTime();
            String y = time.substring(0,4);
            if(!y.equals(year)){
                list2.add(stu_exam);
            }
        }
        list.removeAll(list2);
        return list;
    }

    @Override
    public ScoreVo query(String number, String name) {

        Stu_Exam stu_exam = stu_examRepo.findByNumber(number);
        if(stu_exam==null){
            return null;
        }else {
           User user = userRepo.findByNumber(stu_exam.getCode());
           if(user.getName().equals(name)) {
               ScoreVo scoreVo = new ScoreVo(name,user.getCollege(),number,
                       stu_exam.getHearing()+stu_exam.getReading()+stu_exam.getWriting(),
                       stu_exam.getReading(),stu_exam.getReading(),stu_exam.getWriting());
               return scoreVo;
           }else
               return  null;
        }
    }
}
