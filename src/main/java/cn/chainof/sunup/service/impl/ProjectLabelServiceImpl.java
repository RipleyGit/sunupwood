package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.mapper.ProjectLabelMapper;
import cn.chainof.sunup.model.ProjectLabel;
import cn.chainof.sunup.model.ProjectLabelExample;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.service.ProjectLabelService;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProjectLabelServiceImpl implements ProjectLabelService {

    @Autowired
    private ProjectLabelMapper projectLabelMapper;

    @Override
    public ProjectLabel getLabelByName(String name) {
        ProjectLabelExample example = new ProjectLabelExample();
        example.createCriteria().andNameEqualTo(name).andIsDeletedEqualTo(Const.B_ZERO);
        List<ProjectLabel> list = projectLabelMapper.selectByExample(example);
        if (list == null || list.size() < 1) {
            return null;
        } else {
            return list.get(0);
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addLable(ProjectLabel projectLabel) {
        Long id = KeyUtil.genUniqueKey();
        String labelId = String.valueOf(id);
        ProjectUser user = UserContext.getUserSession();
        projectLabel.setId(labelId);
        projectLabel.setCreateTime(DateUtil.getCurrentDate());
        projectLabel.setIsDeleted(Const.B_ZERO);
        projectLabel.setCreateUser(user.getName());
        projectLabelMapper.insertSelective(projectLabel);//
        return labelId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public Integer deletedLabel(String labelId) {
        int delete = projectLabelMapper.deleteByPrimaryKey(labelId);
        return delete;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public ProjectLabel updateLabel(ProjectLabel label) {
        ProjectLabelExample example = new ProjectLabelExample();
        example.setOrderByClause("update_time DESC");
        example.createCriteria().andNameEqualTo(label.getName())
                .andIsDeletedEqualTo(Const.B_ZERO)
                .andIdNotEqualTo(label.getId());
        List<ProjectLabel> labels = projectLabelMapper.selectByExample(example);
        if (labels != null && labels.size()>0){
            throw new ClientException("该标签已经存在！");
        }
        ProjectLabel projectLabel = projectLabelMapper.selectByPrimaryKey(label.getId());
        projectLabel.setName(label.getName());
        projectLabel.setIsDeleted(Const.B_ZERO);
        projectLabel.setLabelDeclare(label.getLabelDeclare());
        projectLabel.setUpdateTime(DateUtil.getCurrentDate());
        projectLabel.setUpdateUser(UserContext.getUserSession().getName());
        projectLabelMapper.updateByPrimaryKey(projectLabel);
        return projectLabel;
    }

    @Override
    public List<ProjectLabel> queryListByKey(String keyword) {
        List<ProjectLabel> list = new ArrayList<>();
        ProjectLabelExample example = new ProjectLabelExample();
        example.setOrderByClause("update_time DESC");
        if (StringUtil.isNotEmpty(keyword)) {
            keyword = "%" + keyword + "%";
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andNameLike(keyword);
            List<ProjectLabel> listByName = projectLabelMapper.selectByExample(example);
            list.addAll(listByName);
            example = new ProjectLabelExample();
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andLabelDeclareLike(keyword);
            List<ProjectLabel> listByDeclare = projectLabelMapper.selectByExample(example);
            list.addAll(listByDeclare);
        } else {
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO);
            list = projectLabelMapper.selectByExample(example);
        }

        return list;
    }

    @Override
    public ProjectLabel getLabelById(String id) {
        return projectLabelMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProjectLabel getLabelByCode(String labelCode) {
        ProjectLabelExample example = new ProjectLabelExample();
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andLabelCodeEqualTo(labelCode);
        List<ProjectLabel> list = projectLabelMapper.selectByExample(example);
        if (list.size() < 1) {
            return null;
        }
        return list.get(0);
    }
}
