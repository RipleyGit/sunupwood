package cn.chainof.sunup.service.impl;


import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.constant.ModuleConst;
import cn.chainof.sunup.controller.dto.data.ProductDesignDTO;
import cn.chainof.sunup.controller.dto.data.ProductDesignItemDTO;
import cn.chainof.sunup.mapper.ProjectModuleMapper;
import cn.chainof.sunup.model.ProjectModule;
import cn.chainof.sunup.model.ProjectModuleExample;
import cn.chainof.sunup.service.ProductDesignService;
import cn.chainof.sunup.service.module.ProductDesignItemModule;
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
        bannerModule.setIsDeleted(Const.B_ZERO);
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
                .andIsDeletedEqualTo(Const.B_ZERO);
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
        module.setIsDeleted(Const.B_ONE);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return id;
    }

    @Override
    public String addProductDesignItem(ProductDesignItemDTO productItemDto) {
        String id = String.valueOf(KeyUtil.genUniqueKey());
        productItemDto.setId(id);
        ProjectModule bannerModule = getItemProjectModule(productItemDto);
        bannerModule.setIsDeleted(Const.B_ZERO);
        bannerModule.setCreateTime(DateUtil.getCurrentDate());
        bannerModule.setCreateUser(UserContext.getUserSession().getName());
        projectModuleMapper.insertSelective(bannerModule);
        return id;
    }

    @Override
    public ProductDesignItemDTO getProductDesignItemDTOInfo(String id) {
        ProjectModule module = projectModuleMapper.selectByPrimaryKey(id);
        ProductDesignItemDTO dto = getProductDesignItemDTO(module);
        return dto;
    }

    @Override
    public String updateProductDesignItem(ProductDesignItemDTO designDto) {
        ProjectModule module = getItemProjectModule(designDto);
        module.setUpdateTime(DateUtil.getCurrentDate());
        module.setUpdateUser(UserContext.getUserSession().getName());
        projectModuleMapper.updateByPrimaryKeySelective(module);
        return module.getId();
    }

    @Override
    public List<ProductDesignItemDTO> queryProductItemList(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectModuleExample example = new ProjectModuleExample();
        example.setOrderByClause("rank DESC");
        example.createCriteria().andModuleEqualTo(ModuleConst.PRODUCT_DESIGN_ITEM)
                .andIsDeletedEqualTo(Const.B_ZERO);
        List<ProjectModule> moduleList = projectModuleMapper.selectByExampleWithBLOBs(example);
        List<ProductDesignItemDTO> dtoList = new ArrayList<>();
        for (ProjectModule module:moduleList) {
            ProductDesignItemDTO dto = getProductDesignItemDTO(module);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public List<ProductDesignDTO> queryProductListByItem(String itemId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProjectModuleExample example = new ProjectModuleExample();
        example.setOrderByClause("rank DESC");
        example.createCriteria().andModuleEqualTo(ModuleConst.PRODUCTDESIGN)
                .andIsDeletedEqualTo(Const.B_ZERO).andKeywordEqualTo(itemId);
        List<ProjectModule> moduleList = projectModuleMapper.selectByExampleWithBLOBs(example);
        List<ProductDesignDTO> dtoList = new ArrayList<>();
        for (ProjectModule module:moduleList) {
            ProductDesignDTO dto = getProductDesignDTO(module);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private ProjectModule getProjectModule(ProductDesignDTO productDto) {
        ProjectModule bannerModule = new ProjectModule();
        bannerModule.setId(productDto.getId());
        bannerModule.setKeyword(productDto.getItemId());
        bannerModule.setModule(ModuleConst.PRODUCTDESIGN);
        bannerModule.setIntro(productDto.getName()+productDto.getEgName());
        bannerModule.setRank(productDto.getRank());
        ProjectModule item = projectModuleMapper.selectByPrimaryKey(productDto.getItemId());
        ProductDesignModule module = new ProductDesignModule();
        module.setEgName(productDto.getEgName());
        module.setName(productDto.getName());
        module.setItemName(item.getIntro());
        module.setLordImg(productDto.getLordImg());
        module.setIsLarge(productDto.getIsLarge());
        module.setImgUrls(productDto.getImgUrls());
        bannerModule.setContent(JSON.toJSONString(module));
        return bannerModule;
    }

    private ProductDesignDTO getProductDesignDTO(ProjectModule module) {
        ProductDesignDTO dto = new ProductDesignDTO();
        ProductDesignModule designModule = JSON.parseObject(module.getContent(), ProductDesignModule.class);
        ProjectModule item = projectModuleMapper.selectByPrimaryKey(module.getKeyword());
        dto.setId(module.getId());
        dto.setItemId(module.getKeyword());
        dto.setRank(module.getRank());
        dto.setItemName(item.getIntro());
        dto.setName(designModule.getName());
        dto.setEgName(designModule.getEgName());
        dto.setImgUrls(designModule.getImgUrls());
        dto.setLordImg(designModule.getLordImg());
        dto.setIsLarge(designModule.getIsLarge());
        return dto;
    }

    private ProjectModule getItemProjectModule(ProductDesignItemDTO itemDTO) {
        ProjectModule bannerModule = new ProjectModule();
        bannerModule.setId(itemDTO.getId());
        bannerModule.setModule(ModuleConst.PRODUCT_DESIGN_ITEM);
        bannerModule.setIntro(itemDTO.getName());
        bannerModule.setRank(itemDTO.getRank());
        ProductDesignModule module = new ProductDesignModule();
        module.setEgName(itemDTO.getEgName());
        module.setName(itemDTO.getName());
        module.setLordImg(itemDTO.getLordImg());
        bannerModule.setContent(JSON.toJSONString(module));
        return bannerModule;
    }

    private ProductDesignItemDTO getProductDesignItemDTO(ProjectModule module) {
        ProductDesignItemDTO dto = new ProductDesignItemDTO();
        ProductDesignItemModule designModule = JSON.parseObject(module.getContent(), ProductDesignItemModule.class);
        dto.setId(module.getId());
        dto.setRank(module.getRank());
        dto.setName(designModule.getName());
        dto.setEgName(designModule.getEgName());
        dto.setLordImg(designModule.getLordImg());
        return dto;
    }
}
