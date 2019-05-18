package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProjectNews;

import java.util.List;

public interface ProjectNewsService {

    String addProjectNews(ProjectNews news);

    String updateProjectNews(ProjectNews news);

    String deleteById(String id);

    ProjectNews getNewsInfo(String id);

    List<ProjectNews> queryList(String key, Integer pageIndex, Integer pageSize);

    List<ProjectNews> queryListByStyle(String style, Integer pageIndex, Integer pageSize);
}
