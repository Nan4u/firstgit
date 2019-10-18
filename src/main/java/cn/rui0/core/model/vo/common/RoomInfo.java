package cn.rui0.core.model.vo.common;

import lombok.Data;

import java.util.List;

@Data
public class RoomInfo {
    private long id;
    private String name;
    private String situation;
    private List Info;
}
