package cn.rui0.core.service.common.impl;

import cn.rui0.core.dao.common.*;
import cn.rui0.core.model.dto.common.student.UpdateInfoDTO;
import cn.rui0.core.model.po.common.*;
import cn.rui0.core.model.vo.common.Exam_RoomVo;
import cn.rui0.core.model.vo.common.ScoreVo;
import cn.rui0.core.model.vo.common.Stu_ExamVo;
import cn.rui0.core.service.common.StudentService;
import org.hibernate.boot.spi.InFlightMetadataCollector;
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
    public Integer sign_up(User user,  Exam_RoomVo exam_roomVo) {
        String er_number="33037";
        String number=user.getNumber();
        String a = number.substring(4,8);
        String b = String.valueOf(exam_roomVo.getRoom());
        String c = b+"00";
        c=String.valueOf(Integer.parseInt(c)+exam_roomVo.getAmount());
        er_number=er_number+a+c;
        Exam_Room exam_room =exam_roomRepo.findById(exam_roomVo.getRoom()).get();
        Stu_Exam stu_exam = new Stu_Exam(er_number,exam_room,user);
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
    public Exam_RoomVo getRoom(String ex_number) {
        long number = Long.parseLong(ex_number);
        List<Exam_Room> list = exam_roomRepo.findAll();
        List<Exam_RoomVo> list1 = new ArrayList<>();
            for(Exam_Room exam_room :list){
                list1.add(new Exam_RoomVo(exam_room));
            }
            System.out.println(list1);
        for(Exam_RoomVo exam_RoomVo:list1){
            if(exam_RoomVo.getExam()==number&&exam_RoomVo.getAmount()<40) {
                Exam_Room exam_room = exam_roomRepo.findById(exam_RoomVo.getId()).get();
                exam_room.setAmount(exam_RoomVo.getAmount()+1);
                exam_roomRepo.saveAndFlush(exam_room);
                exam_RoomVo.setAmount(exam_room.getAmount());
                return exam_RoomVo;
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
    public List<Stu_ExamVo> FindScore(User user, String year) {
        List<Stu_Exam> list = stu_examRepo.findByUser(user);
        List<Stu_ExamVo> list2 = new ArrayList<>();
        for(Stu_Exam stu_exam:list){
            String isPassed="未通过";
            if(stu_exam.getIsPassed())
                isPassed="通过";
            if(!year.equals("")) {
                String y = stu_exam.getExamRoom().getExam().getTime();
                y = y.substring(0, 4);
                if (y.equals(year))
                    list2.add(new Stu_ExamVo(stu_exam, isPassed, ""));
            }else {
                list2.add(new Stu_ExamVo(stu_exam, isPassed, ""));
            }
        }
        return list2;
    }

    @Override
    public ScoreVo query(String number, String name) {

        Stu_Exam stu_exam = stu_examRepo.findByNumber(number);
        if(stu_exam==null){
            return null;
        }else {
           User user = stu_exam.getUser();
           if(user.getName().equals(name)) {
               ScoreVo scoreVo = new ScoreVo(name,user.getCollege(),number,
                       stu_exam.getHearing()+stu_exam.getReading()+stu_exam.getWriting(),
                       stu_exam.getReading(),stu_exam.getHearing(),stu_exam.getWriting());
               return scoreVo;
           }else
               return  null;
        }
    }

    @Override
    public int updateInfo(User student, UpdateInfoDTO updateInfo) {
        student.setName(updateInfo.getName());
        student.setCollege(updateInfo.getCollege());
        student.setSex(updateInfo.getSex());
        student.setBirthday(updateInfo.getBirthday());
        student.setPhone(updateInfo.getPhone());
        student.setAddress(updateInfo.getAddress());
        userRepo.saveAndFlush(student);
        return  1;
    }
}
