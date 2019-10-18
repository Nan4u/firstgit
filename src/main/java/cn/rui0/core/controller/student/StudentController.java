package cn.rui0.core.controller.student;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.security.annotation.CurrentUser;
import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.dto.common.student.SignUpDTO;
import cn.rui0.core.model.dto.common.student.YearDTO;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Stu_Exam;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.service.common.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by ruilin on 2018/12/10.
 */
@RestController
@Api(tags = {"学生相关"})
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @RequestMapping(value = "/sign_up",method = POST)
    @ApiOperation(value = "学生报名")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult sign_up(@CurrentUser User user, @RequestBody SignUpDTO signUpDTO) {
        User Student = studentService.FindNumber(user);
        String number = Student.getNumber();
        Exam_Room exam_room = studentService.getRoom(signUpDTO.getEx_number());
        if (exam_room!=null){
            if(studentService.sign_up(number,exam_room)==1)
                return ResponseResult.e(ResponseCode.OK);
        }
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/ShowExam",method = POST)
    @ApiOperation("查看考试")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult ShowExam(@CurrentUser User user, @RequestBody ExamDTO examDTO){
        String name = examDTO.getName();
        List<Exam> exam = studentService.ShowExam(name);
        return ResponseResult.e(ResponseCode.OK, exam);
    }
    @RequestMapping(value = "/ShowInfo",method = POST)
    @ApiOperation("查看个人信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult ShowInfo(@CurrentUser User user){
        User user2= studentService.ShowInfo(user);
        return ResponseResult.e(ResponseCode.OK, user2);
    }
    @RequestMapping(value = "/ShowScore",method = POST)
    @ApiOperation("查看成绩")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult ShowScore(@CurrentUser User user,@RequestBody YearDTO yearDTO){
        User Student = studentService.FindNumber(user);
       List<Stu_Exam> list = studentService.FindScore(Student.getNumber(),yearDTO.getYear());
        return ResponseResult.e(ResponseCode.OK, list);
    }
}
