package cn.chainof.sunup.mapper;

import cn.chainof.sunup.model.ProjectUser;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ProjectUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProjectUser record);

    int insertSelective(ProjectUser record);

    ProjectUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProjectUser record);

    int updateByPrimaryKey(ProjectUser record);
}