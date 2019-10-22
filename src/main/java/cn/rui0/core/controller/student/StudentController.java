package cn.rui0.core.controller.student;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.security.annotation.CurrentUser;
import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.dto.common.student.SignUpDTO;
import cn.rui0.core.model.dto.common.student.UpdateInfoDTO;
import cn.rui0.core.model.dto.common.student.YearDTO;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Stu_Exam;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.Exam_RoomVo;
import cn.rui0.core.model.vo.common.Stu_ExamVo;
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
    @RequestMapping(value = "/signUp",method = POST)
    @ApiOperation(value = "学生报名")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult sign_up(@CurrentUser User user, @RequestBody SignUpDTO signUpDTO) {
        User Student = studentService.FindNumber(user);
        Exam_RoomVo exam_roomVo = studentService.getRoom(signUpDTO.getEx_number());
        if (exam_roomVo!=null){
            if(studentService.sign_up(Student,exam_roomVo)==1)
                return ResponseResult.e(ResponseCode.OK);
        }
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/showExam",method = POST)
    @ApiOperation("查看考试")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult ShowExam(@CurrentUser User user, @RequestBody ExamDTO examDTO){
        String name = examDTO.getName();
        List<Exam> exam = studentService.ShowExam(name);
        return ResponseResult.e(ResponseCode.OK, exam);
    }
    @RequestMapping(value = "/showInfo",method = POST)
    @ApiOperation("查看个人信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult ShowInfo(@CurrentUser User user){
        User user2= studentService.ShowInfo(user);
        return ResponseResult.e(ResponseCode.OK, user2);
    }
    @RequestMapping(value = "/updateInfo",method = POST)
    @ApiOperation("修改个人信息")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult updateInfo(@CurrentUser User user,@RequestBody UpdateInfoDTO updateInfo){
        User Student = studentService.FindNumber(user);
        if(studentService.updateInfo(Student,updateInfo)==1)
            return ResponseResult.e(ResponseCode.OK);
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/ShowScore",method = POST)
    @ApiOperation("查看成绩")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"student"})
    public ResponseResult ShowScore(@CurrentUser User user,@RequestBody YearDTO yearDTO){
        User Student = studentService.FindNumber(user);
       List<Stu_ExamVo> list = studentService.FindScore(Student,yearDTO.getYear());
        return ResponseResult.e(ResponseCode.OK, list);
    }
}
