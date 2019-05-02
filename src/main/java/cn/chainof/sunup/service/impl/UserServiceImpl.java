package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.mapper.ProjectUserMapper;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.service.UserService;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    public Long signinUser(String name, String password) {

        ProjectUser user = new ProjectUser();
        Long id = KeyUtil.genUniqueKey();
        user.setId(id);
        user.setName(name);
        user.setPassword(password);
        user.setCreateTime(DateUtil.getCurrentDate());
        user.setCreateUser(name);

        projectUserMapper.insert(user);

        return id;
    }
}
