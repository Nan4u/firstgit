package cn.rui0;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.util.Encrypt;
import cn.rui0.core.dao.common.*;
import cn.rui0.core.model.dto.common.admin.AddInfoDTO;
import cn.rui0.core.model.dto.common.exam.*;
import cn.rui0.core.model.dto.common.student.SignUpDTO;
import cn.rui0.core.model.dto.common.student.YearDTO;
import cn.rui0.core.model.dto.common.user.IdentifyDTO;
import cn.rui0.core.model.dto.common.user.QueryDTO;
import cn.rui0.core.model.dto.common.user.UpdateUserDTo;
import cn.rui0.core.model.dto.common.user.UserRegisterDTO;
import cn.rui0.core.model.po.common.*;
import cn.rui0.core.model.vo.common.*;
import cn.rui0.core.service.common.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppApplicationTests {

    @Autowired
    TextRepo textRepo;
    @Autowired
    Text2Repo text2Repo;
    @Autowired
    StudentService studentService;
    @Autowired
    private UserService userService;
    @Autowired
    UserRepo userRepo;
    @Autowired
    UserRoleRepo userRoleRepo;
    @Autowired
    InfoService infoService;
    @Autowired
    RoomService roomService;
    @Autowired
    ExamService examService;
    @Test
    public void contextLoads() {


    }

    @Test
    @Transactional
    public void a() {
        StuExamIdDTO stuExamIdDTO= new StuExamIdDTO();
        stuExamIdDTO.setId("6");
        stuExamIdDTO.setHearing(100);
        stuExamIdDTO.setReading(100);
        stuExamIdDTO.setWriting(100);
        examService.updateScore(stuExamIdDTO);
        ExamIdDTO examIdDTO = new ExamIdDTO();
        examIdDTO.setNumber("1");
        List<ExamStudentVo> list = examService.findStudent(examIdDTO.getNumber());
        System.out.println(list);
    }
        @Test
    @Transactional
    public void test() {
        User user = new User();
        user.setId(3L);
        SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setEx_number("1");
            User Student = studentService.FindNumber(user);
            Exam_RoomVo exam_roomVo = studentService.getRoom(signUpDTO.getEx_number());
            studentService.sign_up(Student,exam_roomVo);

//        FindUserDTO findUserDTO=new FindUserDTO();
//        findUserDTO.setPageSize(100);
//        System.out.println(findUserDTO.getPageSize());
//        System.out.println(findUserDTO.getPageRequest().toString());




//        User user = userRepo.findById((long) 1).orElse(null);
//        System.out.println(user.getUsername());
////
//
//        User user2 = userRepo.findById((long) 7).orElse(null);
//        Teacher teacher=teacherRepo.findTopByUser(user2);
//        Teacher teacher=new Teacher();
//        teacher.setUser(user2);
//        teacherRepo.save(teacher);


        /*
        Student student = studentRepo.findTopByUser(user);
        if (student == null) {
//            Student s = new Student();
//            s.setUser(user);
//            studentRepo.save(s);
//判断权限

            System.out.println("null");
        } else {


            Course course=courseRepo.findById(Long.parseLong("1544586951232")).orElse(null);

            student.getCourses().add(course);
            studentRepo.save(student);


            System.out.println(student.getId());


        }

*/

//        Student student = studentRepo.findTopByUser(user);
////        Page<Course> coursePage=courseRepo.findByStudents(student, PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createTime")));
//        Page<CourseVo> coursePage=studentService.getCourses(user,PageRequest.of(0, 10, Sort.by(Sort.Direction.DESC, "createTime")));
//        System.out.println(JSON.toJSON(coursePage));

    }
}
