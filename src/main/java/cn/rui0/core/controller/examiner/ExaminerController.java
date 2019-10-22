package cn.rui0.core.controller.examiner;

import cn.rui0.common.bean.ResponseCode;
import cn.rui0.common.bean.ResponseResult;
import cn.rui0.common.constant.Response;
import cn.rui0.common.security.annotation.Access;
import cn.rui0.common.security.annotation.CurrentUser;
import cn.rui0.core.model.dto.common.course.AddCourseDTO;
import cn.rui0.core.model.dto.common.exam.*;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Room;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.ExamManageVo;
import cn.rui0.core.model.vo.common.ExamStudentVo;
import cn.rui0.core.model.vo.common.RoomInfo;
import cn.rui0.core.service.common.ExamService;
import cn.rui0.core.service.common.RoomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.handler.RequestMatchResult;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@Api(tags = {"考试相关"})
@RequestMapping(value = "/examiner")
public class ExaminerController {
    @Autowired
    private ExamService examService;
    @Autowired
    private RoomService roomService;
    @RequestMapping(value = "/AddExam",method = POST)
    @ApiOperation("创建考试")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
        public ResponseResult addExam(@RequestBody ExamDTO examDTO) {
        Exam exam =examService.addExam(examDTO);
        for(Room room:examDTO.getRoom()) {
            Room room1 = roomService.FindRoom(room.getRoom_id());
            if (examService.addExam_Room(exam,room1) != 1)
                return ResponseResult.e(ResponseCode.FAIL);
        }
        return ResponseResult.e(ResponseCode.OK);
    }
    @RequestMapping(value = "/AddRoom",method = POST)
    @ApiOperation("添加考场")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
    public ResponseResult AddRoom(@CurrentUser User user,@RequestBody RoomDTO roomDTO){
        if (roomService.addRoom(user,roomDTO)==1)
            return ResponseResult.e(ResponseCode.OK);
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/RoomInfo",method = POST)
    @ApiOperation("考场查询")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
    public ResponseResult RoomInfo(@RequestBody RoomDTO roomDTO){
        RoomInfo roomInfo = roomService.RoomInfo(roomDTO.getName());
        return ResponseResult.e(ResponseCode.OK,roomInfo);
    }
    @RequestMapping(value = "/UpdateRoom",method = POST)
    @ApiOperation("考场修改")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
    public ResponseResult UpdateRoom(@RequestBody RoomUpdateDTO roomUpdateDTO) {
        if (roomService.UpdateRoom(roomUpdateDTO) == 1)
            return ResponseResult.e(ResponseCode.OK);
        return ResponseResult.e(ResponseCode.FAIL);
    }
    @RequestMapping(value = "/Exam",method = POST)
    @ApiOperation("考试管理")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
    public ResponseResult Exam(@RequestBody ExamManageDTO examManageDTO) {
        List<ExamManageVo> list = examService.findInfo(examManageDTO.getName());
            return ResponseResult.e(ResponseCode.OK,list);
    }
    @RequestMapping(value = "/Exam_Student",method = POST)
    @ApiOperation("考试学生")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
    public ResponseResult Exam_Student(@RequestBody ExamIdDTO examIdDTO) {
        List<ExamStudentVo> list = examService.findStudent(examIdDTO.getNumber());
        return ResponseResult.e(ResponseCode.OK,list);
    }
    @RequestMapping(value = "/updateScore",method = POST)
    @ApiOperation("考试学生")
    @ApiImplicitParam(paramType = "header", name = "Authorization", value = "身份认证Token")
    @Access(roles = {"examiner"})
    public ResponseResult updateScore(@RequestBody StuExamIdDTO stuIdDTO) {
        if ( examService.updateScore(stuIdDTO)!= 1)
            return ResponseResult.e(ResponseCode.FAIL);
        return ResponseResult.e(ResponseCode.OK);
    }
}