package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.mapper.ProjectNewsMapper;
import cn.chainof.sunup.model.ProjectNews;
import cn.chainof.sunup.model.ProjectNewsExample;
import cn.chainof.sunup.service.ProjectNewsService;
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
public class ProjectNewsServiceImpl implements ProjectNewsService {

    @Autowired
    private ProjectNewsMapper projectNewsMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addProjectNews(ProjectNews news) {
        String newsId = String.valueOf(KeyUtil.genUniqueKey());
        news.setId(newsId);
        news.setCreateTime(DateUtil.getCurrentDate());
        news.setCreateUser(UserContext.getUserSession().getName());
        projectNewsMapper.insertSelective(news);
        return newsId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateProjectNews(ProjectNews news) {
        String newsId = news.getId();
        ProjectNews projectNews = projectNewsMapper.selectByPrimaryKey(newsId);
        news.setIsDeleted(Const.B_ZERO);
        news.setCreateTime(projectNews.getCreateTime());
        news.setCreateUser(projectNews.getCreateUser());
        news.setUpdateTime(DateUtil.getCurrentDate());
        news.setUpdateUser(UserContext.getUserSession().getName());
        projectNewsMapper.updateByPrimaryKeyWithBLOBs(news);
        return newsId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deleteById(String id) {
        projectNewsMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public ProjectNews getNewsInfo(String id) {
        return projectNewsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ProjectNews> queryList(String key, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectNewsExample example = new ProjectNewsExample();
        example.setOrderByClause("rank DESC");
        if(StringUtil.isNotEmpty(key)){
            String like = "%" + key + "%";
            ProjectNewsExample.Criteria authorLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andAuthorLike(like);
            example.or(authorLike);
            ProjectNewsExample.Criteria introLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andIntroLike(like);
            example.or(introLike);
            ProjectNewsExample.Criteria titleLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andTitleLike(like);
            example.or(titleLike);
        }else {

            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO);
        }
        return projectNewsMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public List<ProjectNews> queryListByStyle(String style, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectNewsExample example = new ProjectNewsExample();
        example.setOrderByClause("rank DESC");

        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO)
                .andStyeEqualTo(style);
        return projectNewsMapper.selectByExampleWithBLOBs(example);

    }
}
