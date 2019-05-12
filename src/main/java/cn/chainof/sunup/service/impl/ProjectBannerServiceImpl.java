package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.constant.ModuleConst;
import cn.chainof.sunup.controller.dto.data.BannerDTO;
import cn.chainof.sunup.mapper.ProjectModuleMapper;
import cn.chainof.sunup.model.ProjectModule;
import cn.chainof.sunup.model.ProjectModuleExample;
import cn.chainof.sunup.service.ProjectBannerService;
import cn.chainof.sunup.service.module.BannerModule;
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
public class ProjectBannerServiceImpl implements ProjectBannerService {

    @Autowired
    private ProjectModuleMapper projectModuleMapper;

    @Override
    public String addBanner(BannerDTO banner) {
        String id = String.valueOf(KeyUtil.genUniqueKey());
        banner.setId(id);
        ProjectModule bannerModule = getProjectModule(banner);
        bannerModule.setCreateTime(DateUtil.getCurrentDate());
        bannerModule.setCreateUser(UserContext.getUserSession().getName());
        projectModuleMapper.insertSelective(bannerModule);
        return id;
    }



    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deleteBannerById(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        module.setIsDeleted(Const.IS_DELETED);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return id;
    }

    @Override
    public BannerDTO getBannerById(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        BannerDTO dto = getBannerDTO(module);
        return dto;
    }

    @Override
    public List<BannerDTO> queryList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectModuleExample example = new ProjectModuleExample();
        example.createCriteria().andModuleEqualTo(ModuleConst.BANNER)
                .andIsDeletedEqualTo(Const.IS_NORMAL);
        List<ProjectModule> moduleList = projectModuleMapper.selectByExample(example);
        List<BannerDTO> dtoList = new ArrayList<>();
        for (ProjectModule module:moduleList) {
            BannerDTO dto = getBannerDTO(module);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateBanner(BannerDTO banner) {
        ProjectModule module = getProjectModule(banner);
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return module.getId();
    }

    private BannerDTO getBannerDTO(ProjectModule module) {
        BannerDTO dto = new BannerDTO();
        dto.setId(module.getId());
        BannerModule banner = JSON.parseObject(module.getContent(), BannerModule.class);
        dto.setImgUrl(banner.getImgUrl());
        dto.setRank(banner.getRank());
        dto.setTitle(banner.getTitle());
        return dto;
    }

    private ProjectModule getProjectModule(BannerDTO banner) {
        ProjectModule bannerModule = new ProjectModule();
        bannerModule.setId(banner.getId());
        bannerModule.setModule(ModuleConst.BANNER);
        bannerModule.setIntro(banner.getTitle());
        bannerModule.setKeyword(banner.getTitle());
        BannerModule module = new BannerModule();
        module.setRank(banner.getRank());
        module.setImgUrl(banner.getImgUrl());
        module.setTitle(banner.getTitle());
        bannerModule.setContent(JSON.toJSONString(module));
        return bannerModule;
    }
}
