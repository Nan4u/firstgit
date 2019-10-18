package cn.rui0.core.model.dto.common.teacher;

import cn.rui0.core.model.dto.common.SplitPageDTO;
import lombok.Data;

/**
 * Created by ruilin on 2018/12/18.
 */
@Data
public class FindStudentByCourseIdDTO extends SplitPageDTO{
    private Long courseId;
}
