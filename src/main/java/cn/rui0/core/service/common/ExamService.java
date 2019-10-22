package cn.rui0.core.service.common;

import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.dto.common.exam.StuExamIdDTO;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Room;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.ExamManageVo;
import cn.rui0.core.model.vo.common.ExamStudentVo;

import java.util.List;

public interface ExamService {
    //【添加】 考试

    /**
     * 添加考试
     * @param examDTO
     * @return
     */
    Exam addExam(ExamDTO examDTO);
    /**
     * 添加考试教室
     * @param exam
     * @param room
     * @return
     */
    Integer addExam_Room(Exam exam, Room room);
    /**
     * 考试管理
     * @param name
     * @return
     */
    List<ExamManageVo> findInfo(String name);
    /**
     * 学生分数
     * @param number
     * @return
     */
    List<ExamStudentVo> findStudent(String number);
    /**
     * 修改成绩
     * @param stuIdDTO
     * @return
     */
    int updateScore(StuExamIdDTO stuIdDTO);
}
