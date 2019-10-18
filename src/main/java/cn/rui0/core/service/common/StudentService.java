package cn.rui0.core.service.common;

import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Stu_Exam;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.ScoreVo;

import java.util.List;

/**
 * Created by ruilin on 2018/12/11.
 */
public interface StudentService {

    /**
     * 考试报名
     * @param number
     * @param exam_room
     * @return
     */
    Integer sign_up(String number, Exam_Room exam_room);
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
    Exam_Room getRoom(String ex_number);
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
     * @param number
     * @param year
     * @return
     */
    List<Stu_Exam> FindScore(String number, String year);
    /**
     * 查询成绩
     * @param number
     * @param name
     * @return
     */
    ScoreVo query(String number, String name);
}
