package cn.rui0.core.model.po.common;

import cn.rui0.core.model.dto.common.admin.AddInfoDTO;
import cn.rui0.core.model.po.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
public class Info extends BaseEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long in_id;
    private String name;
    private String title;
    private String summary;
    private String text;

    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "us_number")
    private User user;

    public Info(AddInfoDTO addInfoDTO,User user){
        this.name=addInfoDTO.getName();
        this.title=addInfoDTO.getTitle();
        this.summary=addInfoDTO.getSummary();
        this.text=addInfoDTO.getText();
        this.user=user;
    }
}
