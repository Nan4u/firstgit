package cn.rui0.core.service.common.impl;

import cn.rui0.core.dao.common.InfoRepo;
import cn.rui0.core.dao.common.UserRepo;
import cn.rui0.core.model.dto.common.admin.AddInfoDTO;
import cn.rui0.core.model.po.common.Info;
import cn.rui0.core.model.po.common.User;
import cn.rui0.core.service.common.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoRepo infoRepo;
    @Autowired
    UserRepo userRepo;
    @Override
    public int addInfo(User user, AddInfoDTO addInfoDTO) {
        User user1 = userRepo.findById(user.getId()).get();
        Info info = new Info(addInfoDTO,user1);
        infoRepo.saveAndFlush(info);
        return 1;
    }

    @Override
    public List<Info> ShowAll() {
        List<Info> list = infoRepo.findAll();
        return list;
    }
}
