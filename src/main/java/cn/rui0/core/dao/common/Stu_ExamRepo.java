package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.Stu_Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Stu_ExamRepo extends JpaRepository<Stu_Exam,Long> {
    List<Stu_Exam> findByCode(String number);

    Stu_Exam findByNumber(String number);
}
