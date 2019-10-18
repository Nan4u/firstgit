package cn.rui0.core.model.dto.common.student;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by ruilin on 2018/12/15.
 */
@Data
public class ImportStudentDTO {
    private Long studentId;
    private Long courseId;
    private MultipartFile file;
}
