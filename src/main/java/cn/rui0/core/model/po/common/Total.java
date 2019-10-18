package cn.rui0.core.model.po.common;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Total {
    @Id
    private long ID;
    private int number;
}
