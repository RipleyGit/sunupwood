package cn.chainof.sunup.mapper;

import cn.chainof.sunup.model.ProjectDesigner;
import cn.chainof.sunup.model.ProjectDesignerExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
@Mapper
public interface ProjectDesignerMapper {
    long countByExample(ProjectDesignerExample example);

    int deleteByExample(ProjectDesignerExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectDesigner record);

    int insertSelective(ProjectDesigner record);

    List<ProjectDesigner> selectByExampleWithBLOBs(ProjectDesignerExample example);

    List<ProjectDesigner> selectByExample(ProjectDesignerExample example);

    ProjectDesigner selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectDesigner record, @Param("example") ProjectDesignerExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectDesigner record, @Param("example") ProjectDesignerExample example);

    int updateByExample(@Param("record") ProjectDesigner record, @Param("example") ProjectDesignerExample example);

    int updateByPrimaryKeySelective(ProjectDesigner record);

    int updateByPrimaryKeyWithBLOBs(ProjectDesigner record);

    int updateByPrimaryKey(ProjectDesigner record);
}