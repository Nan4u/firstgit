package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.Student;
import cn.rui0.core.model.po.common.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruilin on 2018/12/9.
 */
public interface StudentRepo extends JpaRepository<Student,Long> {
}
