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
        news.setCreateTime(projectNews.getCreateTime());
        news.setCreateUser(projectNews.getCreateUser());
        news.setUpdateTime(DateUtil.getCurrentDate());
        news.setUpdateUser(UserContext.getUserSession().getName());
        projectNewsMapper.updateByPrimaryKey(news);
        return newsId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deleteById(String id) {
        ProjectNews projectNews = projectNewsMapper.selectByPrimaryKey(id);
        projectNews.setIsDeleted(Const.IS_DELETED);
        projectNewsMapper.updateByPrimaryKey(projectNews);
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
        example.setOrderByClause("update_time DESC");
        if(StringUtil.isNotEmpty(key)){
            String like = "%" + key + "%";
            ProjectNewsExample.Criteria authorLike = example.createCriteria().andAuthorLike(like);
            example.or(authorLike);
            ProjectNewsExample.Criteria introLike = example.createCriteria().andIntroLike(like);
            example.or(introLike);
            ProjectNewsExample.Criteria titleLike = example.createCriteria().andTitleLike(like);
            example.or(titleLike);
        }
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL);
        return projectNewsMapper.selectByExampleWithBLOBs(example);
    }
}
