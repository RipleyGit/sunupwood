package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProjectLabel;

import java.util.List;

public interface ProjectLabelService {

    ProjectLabel getLabelByName(String name);

    String addLable(ProjectLabel projectLabel);

    Integer deletedLabel(String labelId);

    ProjectLabel updateLabel(ProjectLabel label);

    List<ProjectLabel> queryListByKey(String keyword);

    ProjectLabel getLabelById(String id);

    ProjectLabel getLabelByCode(String labelCode);

    List<ProjectLabel> queryListByIds(List<String> labels);
}
