package cn.rui0.core.service.common;

import cn.rui0.core.model.dto.common.student.UpdateInfoDTO;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Stu_Exam;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.Exam_RoomVo;
import cn.rui0.core.model.vo.common.ScoreVo;
import cn.rui0.core.model.vo.common.Stu_ExamVo;

import java.util.List;

/**
 * Created by ruilin on 2018/12/11.
 */
public interface StudentService {

    /**
     * 考试报名
     * @param user
     * @param exam_RoomVo
     * @return
     */
    Integer sign_up(User user, Exam_RoomVo exam_RoomVo);
    /**
     * 考试报名
     * @param name
     * @return
     */
    List<Exam> ShowExam(String name);
    /**
     * 考场余额
     * @param ex_number
     * @return
     */
    Exam_RoomVo getRoom(String ex_number);
    /**
     * 查看个人信息
     * @param user
     * @return
     */
    User ShowInfo(User user);
    /**
     * 查看学号
     * @param user
     * @return
     */
    User FindNumber(User user);
    /**
     * 查看成绩
     * @param user
     * @param year
     * @return
     */
    List<Stu_ExamVo> FindScore(User user, String year);
    /**
     * 查询成绩
     * @param number
     * @param name
     * @return
     */
    ScoreVo query(String number, String name);
    /**
     * 修改个人信息
     * @param student
     * @param updateInfo
     * @return
     */
    int updateInfo(User student, UpdateInfoDTO updateInfo);
}
