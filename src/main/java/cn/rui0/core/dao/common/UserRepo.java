package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByIdentify(String identify);

    User findByNumber(String number);

    User findByName(String username);
}
