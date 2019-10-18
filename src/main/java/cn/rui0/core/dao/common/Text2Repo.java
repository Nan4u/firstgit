package cn.rui0.core.dao.common;


import cn.rui0.core.model.po.common.Test1;
import cn.rui0.core.model.po.common.Test2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Text2Repo  extends JpaRepository<Test2,Long> {

    List<Test2> findByName2(String name);
}
