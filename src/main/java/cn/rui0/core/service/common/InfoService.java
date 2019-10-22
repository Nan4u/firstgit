package cn.rui0.core.service.common;


import cn.rui0.core.model.dto.common.admin.AddInfoDTO;
import cn.rui0.core.model.po.common.Info;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.model.vo.common.InfoVo;

import java.util.List;

public interface InfoService {
    int addInfo(User user, AddInfoDTO addInfoDTO);

   List<InfoVo> ShowAll();
}
