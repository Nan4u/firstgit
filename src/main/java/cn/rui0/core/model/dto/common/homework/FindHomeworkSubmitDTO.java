package cn.rui0.core.model.dto.common.homework;

import cn.rui0.core.model.dto.common.SplitPageDTO;
import lombok.Data;

/**
 * Created by ruilin on 2018/12/16.
 */
@Data
public class FindHomeworkSubmitDTO extends SplitPageDTO {
    private Long homeworkId;
}
