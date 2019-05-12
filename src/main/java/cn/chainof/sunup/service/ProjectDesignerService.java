package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProjectDesigner;

import java.util.List;

public interface ProjectDesignerService {
    ProjectDesigner getDesignerByName(String name);

    String addDesigner(ProjectDesigner newDesigner);

    String deletedById(String id);

    ProjectDesigner getDesignerById(String id);

    String updateDesigner(ProjectDesigner updateDesigner);

    List<ProjectDesigner> queryList(String key, Integer pageIndex, Integer pageSize);
}
