package cn.rui0.core.model.vo.common;

import cn.rui0.core.model.po.common.Info;
import lombok.Data;

import java.util.Date;

@Data
public class InfoVo {
    private String name;
    private String summary;
    private String title;
    private String text;
    private String userName;
    private Date createTime;

    public InfoVo(Info info){
        this.name=info.getName();
        this.summary=info.getSummary();
        this.title=info.getTitle();
        this.text=info.getText();
        this.userName=info.getUser().getName();
        this.createTime=info.getCreateTime();
    }
}
