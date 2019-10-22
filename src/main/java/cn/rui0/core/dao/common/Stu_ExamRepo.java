package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.Exam_Room;
import cn.rui0.core.model.po.common.Stu_Exam;
import cn.rui0.core.model.po.common.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Stu_ExamRepo extends JpaRepository<Stu_Exam,Long> {
    List<Stu_Exam> findByUser(User user);

    Stu_Exam findByNumber(String number);

    List<Stu_Exam> findByExamRoom(Exam_Room exam_room);
}
