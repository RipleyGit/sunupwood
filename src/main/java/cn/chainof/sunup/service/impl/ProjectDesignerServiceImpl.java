package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.mapper.ProjectDesignerMapper;
import cn.chainof.sunup.model.ProjectDesigner;
import cn.chainof.sunup.model.ProjectDesignerExample;
import cn.chainof.sunup.service.ProjectDesignerService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class ProjectDesignerServiceImpl implements ProjectDesignerService {

    @Autowired
    private ProjectDesignerMapper projectDesignerMapper;

    @Override
    public ProjectDesigner getDesignerByName(String name) {

        ProjectDesignerExample example = new ProjectDesignerExample();
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andNameEqualTo(name);
        List<ProjectDesigner> list = projectDesignerMapper.selectByExample(example);
        if (list == null || list.size() < 1) {
            return null;
        }
        return list.get(0);

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addDesigner(ProjectDesigner newDesigner) {
        ProjectDesigner designer = getDesignerByName(newDesigner.getName());
        if (designer != null){
            log.error("已存在该设计师");
            throw new ClientException("已存在该设计师");
        }
        String id = String.valueOf(KeyUtil.genUniqueKey());
        newDesigner.setId(id);
        if (StringUtil.isEmpty(newDesigner.getSamplereels())){
            newDesigner.setSamplereels("[]");
        }
        newDesigner.setCreateTime(DateUtil.getCurrentDate());
        newDesigner.setCreateUser(UserContext.getUserSession().getName());
        projectDesignerMapper.insertSelective(newDesigner);
        return id;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deletedById(String id) {
        projectDesignerMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public ProjectDesigner getDesignerById(String id) {
        return projectDesignerMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateDesigner(ProjectDesigner updateDesigner) {

        ProjectDesignerExample example = new ProjectDesignerExample();
        example.createCriteria().andNameEqualTo(updateDesigner.getName())
                .andIdNotEqualTo(updateDesigner.getId())
                .andIsDeletedEqualTo(Const.B_ZERO);
        List<ProjectDesigner> list = projectDesignerMapper.selectByExample(example);

        if (list != null && list.size()>0){
            log.error("已存在该设计师");
            throw new ClientException("已存在该设计师");
        }
        ProjectDesigner designer = projectDesignerMapper.selectByPrimaryKey(updateDesigner.getId());
        AutoConvertUtil.nullAutoFill(updateDesigner,designer);
        if (StringUtil.isEmpty(updateDesigner.getSamplereels())){
            updateDesigner.setSamplereels("[]");
        }
        updateDesigner.setIsDeleted(Const.B_ZERO);
        updateDesigner.setUpdateTime(DateUtil.getCurrentDate());
        updateDesigner.setUpdateUser(UserContext.getUserSession().getName());
        projectDesignerMapper.updateByPrimaryKeyWithBLOBs(updateDesigner);
        return updateDesigner.getId();
    }

    @Override
    public List<ProjectDesigner> queryList(String key, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectDesignerExample example = new ProjectDesignerExample();
        example.setOrderByClause("update_time DESC");
        if (StringUtil.isEmpty(key)){
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO);
            return projectDesignerMapper.selectByExampleWithBLOBs(example);
        }
        String like = "%" + key + "%";
        ProjectDesignerExample.Criteria nameLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andNameLike(like);
        example.or(nameLike);
        ProjectDesignerExample.Criteria ageLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andAgeLike(like);
        example.or(ageLike);
        ProjectDesignerExample.Criteria createUserLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andCreateUserLike(like);
        example.or(createUserLike);
        ProjectDesignerExample.Criteria updateUserLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andUpdateUserLike(like);
        example.or(updateUserLike);
        ProjectDesignerExample.Criteria sexLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andSexLike(like);
        example.or(sexLike);
        ProjectDesignerExample.Criteria introduceLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andIntroduceLike(like);
        example.or(introduceLike);
        return projectDesignerMapper.selectByExampleWithBLOBs(example);
    }
}
