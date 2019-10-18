package cn.rui0;

import cn.rui0.core.dao.common.*;
import cn.rui0.core.model.dto.common.user.IdentifyDTO;
import cn.rui0.core.model.dto.common.user.UpdateUserDTo;
import cn.rui0.core.model.po.common.*;
import cn.rui0.core.model.vo.common.IdentifyVo;
import cn.rui0.core.service.common.StudentService;
import cn.rui0.core.service.common.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
    @Test
    public void contextLoads() {


    }

    @Test
    @Transactional
    public void a() {
//        UpdateUserDTo updateUserDTo = new UpdateUserDTo();
//        updateUserDTo.setName("fengyinan");
//        updateUserDTo.setPhone("17857342199");
//        updateUserDTo.setNumber("123456");
//        updateUserDTo.setPwd("e10adc3949ba59abbe56e057f20f883e");
//        updateUserDTo.setPower("student");
//        userService.updateUser(updateUserDTo);
    }
        @Test
    @Transactional
    public void test() {


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
