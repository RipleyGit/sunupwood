package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProjectUser;

import java.util.List;

public interface ProjectUserService {

    Long addNewUser(String name, String password);

    ProjectUser getByName(String name);

    List<ProjectUser> queryList(Integer pageIndex, Integer pageSize);

    String addNewUser(ProjectUser projectUser);

    String updateUser(ProjectUser projectUser);
}
