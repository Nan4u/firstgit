package cn.rui0.core.model.po.common;

import cn.rui0.core.model.po.base.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class UserRole extends BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;
    @NotFound(action= NotFoundAction.IGNORE)
    @JsonBackReference
    @ManyToOne(targetEntity = User.class )
    private User user;
    private String role;

}