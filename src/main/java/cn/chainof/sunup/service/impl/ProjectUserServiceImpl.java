package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.mapper.ProjectUserMapper;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.model.ProjectUserExample;
import cn.chainof.sunup.service.ProjectUserService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Slf4j
@Service
public class ProjectUserServiceImpl implements ProjectUserService {

    @Autowired
    private ProjectUserMapper projectUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public Long addNewUser(String name, String password) {
        ProjectUserExample example = new ProjectUserExample();
        example.createCriteria().andNameEqualTo(name).andIsDeletedEqualTo(Const.IS_NORMAL);
        List<ProjectUser> list = projectUserMapper.selectByExample(example);
        if (list != null && list.size() >0){
            throw new ClientException("用户名已存在！");
        }
        ProjectUser user = new ProjectUser();
        Long id = KeyUtil.genUniqueKey();
        user.setId(String.valueOf(id));
        user.setName(name);
        user.setPassword(password);
        user.setCreateTime(DateUtil.getCurrentDate());
        user.setCreateUser(name);
        projectUserMapper.insert(user);
        return id;
    }

    @Override
    public ProjectUser getByName(String name) {
        ProjectUserExample example = new ProjectUserExample();
        example.createCriteria().andNameEqualTo(name).andIsDeletedEqualTo(Const.IS_NORMAL);
        List<ProjectUser> list = projectUserMapper.selectByExample(example);
        if (list == null || list.size() <1) {
            return null;
        } else {
            return list.get(0);
        }
    }

    @Override
    public List<ProjectUser> queryList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectUserExample example = new ProjectUserExample();
        example.setOrderByClause("create_time DESC");
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL);
        List<ProjectUser> list = projectUserMapper.selectByExample(example);
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addNewUser(ProjectUser projectUser) {
        ProjectUserExample example = new ProjectUserExample();
        example.createCriteria().andNameEqualTo(projectUser.getName()).andIsDeletedEqualTo(Const.IS_NORMAL);
        List<ProjectUser> list = projectUserMapper.selectByExample(example);
        if (list != null && list.size() >0){
            throw new ClientException("用户名已存在！");
        }
        String id = String.valueOf(KeyUtil.genUniqueKey());
        projectUser.setId(id);
        projectUser.setCreateTime(DateUtil.getCurrentDate());
        projectUser.setCreateUser(projectUser.getName());
        projectUserMapper.insertSelective(projectUser);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateUser(ProjectUser projectUser) {
        ProjectUserExample example = new ProjectUserExample();
        example.createCriteria().andNameEqualTo(projectUser.getName())
                .andIsDeletedEqualTo(Const.IS_NORMAL)
                .andIdNotEqualTo(projectUser.getId());
        List<ProjectUser> list = projectUserMapper.selectByExample(example);
        if (list != null && list.size() >0){
            throw new ClientException("用户名已存在！");
        }
        ProjectUser user = projectUserMapper.selectByPrimaryKey(projectUser.getId());
        AutoConvertUtil.nullAutoFill(user,projectUser);
        projectUser.setUpdateTime(DateUtil.getCurrentDate());
        projectUser.setUpdateUser(UserContext.getUserSession().getName());
        return projectUser.getId();

    }


}