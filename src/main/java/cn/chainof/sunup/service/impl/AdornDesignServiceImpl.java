package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.constant.ModuleConst;
import cn.chainof.sunup.controller.dto.data.AdornDesignDTO;
import cn.chainof.sunup.mapper.ProjectModuleMapper;
import cn.chainof.sunup.model.ProjectModule;
import cn.chainof.sunup.model.ProjectModuleExample;
import cn.chainof.sunup.service.AdornDesignService;
import cn.chainof.sunup.service.module.AdornDesignModule;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AdornDesignServiceImpl implements AdornDesignService {

    @Autowired
    private ProjectModuleMapper projectModuleMapper;


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addAdorn(AdornDesignDTO adornDto) {
        String id = String.valueOf(KeyUtil.genUniqueKey());
        adornDto.setId(id);
        ProjectModule bannerModule = getProjectModule(adornDto);
        bannerModule.setCreateTime(DateUtil.getCurrentDate());
        bannerModule.setCreateUser(UserContext.getUserSession().getName());
        projectModuleMapper.insertSelective(bannerModule);
        return id;
    }


    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deleteById(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        module.setIsDeleted(Const.B_ONE);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return id;
    }

    @Override
    public AdornDesignDTO getAdornInfoById(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        AdornDesignDTO dto = getProductDesignDTO(module);
        return dto;
    }



    @Override
    public List<AdornDesignDTO> queryAdornList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectModuleExample example = new ProjectModuleExample();
        example.setOrderByClause("rank DESC");
        example.createCriteria().andModuleEqualTo(ModuleConst.ADORNDESIGN)
                .andIsDeletedEqualTo(Const.B_ZERO);
        List<ProjectModule> moduleList = projectModuleMapper.selectByExampleWithBLOBs(example);
        List<AdornDesignDTO> dtoList = new ArrayList<>();
        for (ProjectModule module:moduleList) {
            AdornDesignDTO dto = getProductDesignDTO(module);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateAdorn(AdornDesignDTO adornDto) {
        ProjectModule module = getProjectModule(adornDto);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return module.getId();
    }


    private ProjectModule getProjectModule(AdornDesignDTO adornDto) {
        ProjectModule module = new ProjectModule();
        module.setId(adornDto.getId());
        module.setIntro(adornDto.getName());
        module.setKeyword(adornDto.getName());
        AdornDesignModule adorn = new AdornDesignModule();
        module.setRank(adornDto.getRank());
        adorn.setImgUrls(adornDto.getImgUrls());
        adorn.setLordImg(adornDto.getLordImg());
        adorn.setName(adornDto.getName());
        module.setModule(ModuleConst.ADORNDESIGN);
        module.setContent(JSON.toJSONString(adorn));
        return module;
    }

    private AdornDesignDTO getProductDesignDTO(ProjectModule module) {
        AdornDesignDTO dto = new AdornDesignDTO();
        dto.setId(module.getId());
        dto.setRank(module.getRank());
        AdornDesignModule designModule = JSON.parseObject(module.getContent(), AdornDesignModule.class);
        dto.setName(designModule.getName());
        dto.setLordImg(designModule.getLordImg());
        dto.setImgUrls(designModule.getImgUrls());
        return dto;
    }
}
