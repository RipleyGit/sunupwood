package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProjectLabel;

import java.util.List;

public interface ProjectLabelService {

    ProjectLabel getLabelByName(String name);

    Long addLable(ProjectLabel projectLabel);

    Integer deletedLabel(Long labelId);

    ProjectLabel updateLabel(ProjectLabel label);

    List<ProjectLabel> queryListByKey(String keyword);

    ProjectLabel getLabelById(String id);
}
