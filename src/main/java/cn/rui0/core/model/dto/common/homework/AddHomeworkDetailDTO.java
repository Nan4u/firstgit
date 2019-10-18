package cn.rui0.core.model.dto.common.homework;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ruilin on 2018/12/14.
 */
@Data
public class AddHomeworkDetailDTO {
    private String answer;
    private MultipartFile file;

    public AddHomeworkDetailDTO(){}


    public AddHomeworkDetailDTO(String answer, MultipartFile file) {
        this.answer = answer;
        this.file = file;
    }
}
