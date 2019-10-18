package cn.rui0.core.model.dto.common.homework;

import cn.rui0.core.model.dto.common.SplitPageDTO;
import lombok.Data;

/**
 * Created by ruilin on 2018/12/12.
 */
@Data
public class FindHomeworkDTO extends SplitPageDTO {
    private Long courseId;
}
