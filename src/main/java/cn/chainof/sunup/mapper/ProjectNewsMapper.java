package cn.chainof.sunup.mapper;

import cn.chainof.sunup.model.ProjectNews;
import cn.chainof.sunup.model.ProjectNewsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectNewsMapper {
    long countByExample(ProjectNewsExample example);

    int deleteByExample(ProjectNewsExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProjectNews record);

    int insertSelective(ProjectNews record);

    List<ProjectNews> selectByExampleWithBLOBs(ProjectNewsExample example);

    List<ProjectNews> selectByExample(ProjectNewsExample example);

    ProjectNews selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProjectNews record, @Param("example") ProjectNewsExample example);

    int updateByExampleWithBLOBs(@Param("record") ProjectNews record, @Param("example") ProjectNewsExample example);

    int updateByExample(@Param("record") ProjectNews record, @Param("example") ProjectNewsExample example);

    int updateByPrimaryKeySelective(ProjectNews record);

    int updateByPrimaryKeyWithBLOBs(ProjectNews record);

    int updateByPrimaryKey(ProjectNews record);
}