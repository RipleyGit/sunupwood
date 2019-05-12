package cn.chainof.sunup.mapper;

import cn.chainof.sunup.model.ProjectModule;
import cn.chainof.sunup.model.ProjectModuleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectModuleMapper {
    long countByExample(ProjectModuleExample example);

    int deleteByExample(ProjectModuleExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectModule record);

    int insertSelective(ProjectModule record);

    List<ProjectModule> selectByExampleWithBLOBs(ProjectModuleExample example);

    List<ProjectModule> selectByExample(ProjectModuleExample example);

    ProjectModule selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectModule record, @Param("example") ProjectModuleExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectModule record, @Param("example") ProjectModuleExample example);

    int updateByExample(@Param("record") ProjectModule record, @Param("example") ProjectModuleExample example);

    int updateByPrimaryKeySelective(ProjectModule record);

    int updateByPrimaryKeyWithBLOBs(ProjectModule record);

    int updateByPrimaryKey(ProjectModule record);
}