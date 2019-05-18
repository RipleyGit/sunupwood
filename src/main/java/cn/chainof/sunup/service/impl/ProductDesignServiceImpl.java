package cn.chainof.sunup.service.impl;


import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.constant.ModuleConst;
import cn.chainof.sunup.controller.dto.data.ProductDesignDTO;
import cn.chainof.sunup.mapper.ProjectModuleMapper;
import cn.chainof.sunup.model.ProjectModule;
import cn.chainof.sunup.model.ProjectModuleExample;
import cn.chainof.sunup.service.ProductDesignService;
import cn.chainof.sunup.service.module.ProductDesignModule;
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
public class ProductDesignServiceImpl implements ProductDesignService {

    @Autowired
    private ProjectModuleMapper projectModuleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addProductDesign(ProductDesignDTO productDto) {
        String id = String.valueOf(KeyUtil.genUniqueKey());
        productDto.setId(id);
        ProjectModule bannerModule = getProjectModule(productDto);
        bannerModule.setIsDeleted(Const.IS_NORMAL);
        bannerModule.setCreateTime(DateUtil.getCurrentDate());
        bannerModule.setCreateUser(UserContext.getUserSession().getName());
        projectModuleMapper.insertSelective(bannerModule);
        return id;
    }



    @Override
    public ProductDesignDTO getProductDesignDTOInfo(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        ProductDesignDTO dto = getProductDesignDTO(module);
        return dto;
    }



    @Override
    public List<ProductDesignDTO> queryProductList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectModuleExample example = new ProjectModuleExample();
        example.setOrderByClause("rank DESC");
        example.createCriteria().andModuleEqualTo(ModuleConst.PRODUCTDESIGN)
                .andIsDeletedEqualTo(Const.IS_NORMAL);
        List<ProjectModule> moduleList = projectModuleMapper.selectByExampleWithBLOBs(example);
        List<ProductDesignDTO> dtoList = new ArrayList<>();
        for (ProjectModule module:moduleList) {
            ProductDesignDTO dto = getProductDesignDTO(module);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateProductDesign(ProductDesignDTO designDto) {
        ProjectModule module = getProjectModule(designDto);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return module.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deleteById(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        module.setIsDeleted(Const.IS_DELETED);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return id;
    }

    private ProjectModule getProjectModule(ProductDesignDTO productDto) {
        ProjectModule bannerModule = new ProjectModule();
        bannerModule.setId(productDto.getId());
        bannerModule.setModule(ModuleConst.PRODUCTDESIGN);
        bannerModule.setIntro(productDto.getName()+productDto.getEgName());
        bannerModule.setRank(productDto.getRank());
        ProductDesignModule module = new ProductDesignModule();
        module.setEgName(productDto.getEgName());
        module.setName(productDto.getName());
        module.setLordImg(productDto.getLordImg());
        module.setIsLarge(productDto.getIsLarge());
        module.setImgUrls(productDto.getImgUrls());
        bannerModule.setContent(JSON.toJSONString(module));
        return bannerModule;
    }

    private ProductDesignDTO getProductDesignDTO(ProjectModule module) {
        ProductDesignDTO dto = new ProductDesignDTO();
        ProductDesignModule designModule = JSON.parseObject(module.getContent(), ProductDesignModule.class);
        dto.setId(module.getId());
        dto.setRank(module.getRank());
        dto.setName(designModule.getName());
        dto.setEgName(designModule.getEgName());
        dto.setImgUrls(designModule.getImgUrls());
        dto.setLordImg(designModule.getLordImg());
        dto.setIsLarge(designModule.getIsLarge());
        return dto;
    }
}
