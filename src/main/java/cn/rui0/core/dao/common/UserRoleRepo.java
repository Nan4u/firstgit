package cn.rui0.core.dao.common;

import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.po.common.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ruilin on 2018/12/6.
 */
public interface UserRoleRepo  extends JpaRepository<UserRole, Long> {

    UserRole findByUser(User user);
}
