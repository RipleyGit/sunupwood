package cn.chainof.sunup.mapper;

import cn.chainof.sunup.model.ProjectLabel;
import cn.chainof.sunup.model.ProjectLabelExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProjectLabelMapper {
    long countByExample(ProjectLabelExample example);

    int deleteByExample(ProjectLabelExample example);

    int deleteByPrimaryKey(Long id);

    int insert(ProjectLabel record);

    int insertSelective(ProjectLabel record);

    List<ProjectLabel> selectByExample(ProjectLabelExample example);

    ProjectLabel selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") ProjectLabel record, @Param("example") ProjectLabelExample example);

    int updateByExample(@Param("record") ProjectLabel record, @Param("example") ProjectLabelExample example);

    int updateByPrimaryKeySelective(ProjectLabel record);

    int updateByPrimaryKey(ProjectLabel record);
}