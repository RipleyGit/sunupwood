package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProjectUser;

public interface UserService {

    Long addNewUser(String name, String password);

    ProjectUser getByName(String name);
}
