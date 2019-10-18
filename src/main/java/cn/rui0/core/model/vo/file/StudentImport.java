package cn.rui0.core.model.vo.file;

import lombok.Data;

import java.util.List;

/**
 * Created by ruilin on 2018/12/15.
 */
@Data
public class StudentImport {
    private String studentNumber;
    public StudentImport(List<String> list){
        this.studentNumber=list.get(0);
    }
}
