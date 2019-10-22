package cn.rui0.core.model.vo.common;

import lombok.Data;

@Data
public class IdentifyVo {
    private String message;
    //核验结果状态码，1 一致；2 不一致；3 库无
    private String res;

    private String name;

    private String cardID;
}
