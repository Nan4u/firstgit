package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepo extends JpaRepository<Exam, Long> {
    List<Exam> findByName(String name);
}
