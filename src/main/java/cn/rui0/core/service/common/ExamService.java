package cn.rui0.core.service.common;

import cn.rui0.core.model.dto.common.exam.ExamDTO;
import cn.rui0.core.model.po.common.Exam;
import cn.rui0.core.model.po.common.Room;
import cn.rui0.core.model.po.common.User;

public interface ExamService {
    //【添加】 考试

    /**
     * 添加考试
     * @param examDTO
     * @return
     */
    Exam addExam(ExamDTO examDTO);
    /**
     * 添加考试教师
     * @param exam
     * @param room
     * @return
     */
    Integer addExam_Room(Exam exam, Room room);
}
