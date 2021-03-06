package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.controller.dto.data.ProductArray;
import cn.chainof.sunup.controller.dto.data.ProductDTO;
import cn.chainof.sunup.mapper.ProductMapper;
import cn.chainof.sunup.model.Product;
import cn.chainof.sunup.model.ProductExample;
import cn.chainof.sunup.model.ProductItem;
import cn.chainof.sunup.model.ProjectLabel;
import cn.chainof.sunup.service.ProductItemService;
import cn.chainof.sunup.service.ProductService;
import cn.chainof.sunup.service.ProjectLabelService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductItemService productItemService;

    @Autowired
    private ProjectLabelService projectLabelService;

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addProductInfo(Product product) {
        String productId = String.valueOf(KeyUtil.genUniqueKey());
        product.setId(productId);
        product.setIsDeleted(Const.B_ZERO);
        if (StringUtil.isEmpty(product.getImgUrls())) {
            product.setImgUrls("{}");
        }
        if (StringUtil.isEmpty(product.getLabels())) {
            product.setLabels("{}");
        }
        product.setCreateTime(DateUtil.getCurrentDate());
        product.setCreateUser(UserContext.getUserSession().getName());
        productMapper.insertSelective(product);
        return productId;
    }

    @Override
    public ProductArray queryProducts(String key, Integer pageIndex, Integer pageSize) {
        List<ProductItem> itemList = new ArrayList<>();
        if (StringUtil.isNotEmpty(key)){
            itemList = productItemService.queryListByName(key);
        }
        PageHelper.startPage(pageIndex, pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");

        if (StringUtil.isNotEmpty(key)) {
            String likeKey = "%" + key + "%";
            ProductExample.Criteria nameLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andNameLike(likeKey);
            example.or(nameLike);
            ProductExample.Criteria declareLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andProductDeclareLike(likeKey);
            example.or(declareLike);
            ProductExample.Criteria priceLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andPriceLike(likeKey);
            example.or(priceLike);
            ProductExample.Criteria amountLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andAmountLike(likeKey);
            example.or(amountLike);
            ProductExample.Criteria measureLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andMeasureLike(likeKey);
            example.or(measureLike);
            ProductExample.Criteria salesLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andSalesLike(likeKey);
            example.or(salesLike);
            ProductExample.Criteria textureLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andTextureLike(likeKey);
            example.or(textureLike);
            ProductExample.Criteria userLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andCreateUserLike(likeKey);
            example.or(userLike);
            ProductExample.Criteria updateUserLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andUpdateUserLike(likeKey);
            example.or(updateUserLike);
            if (itemList != null && itemList.size()>0){
                ProductExample.Criteria itemLike = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemIdIn(itemList.stream().map(ProductItem::getId).collect(Collectors.toList()));
                example.or(itemLike);
            }
        }else {
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO);
        }
        List<Product> list = productMapper.selectByExample(example);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        ProductArray array = new ProductArray();
        array.setProductList(getDtoList(list));
        cn.chainof.sunup.controller.dto.data.PageInfo page = new cn.chainof.sunup.controller.dto.data.PageInfo();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        Long total = Long.valueOf(pageInfo.getTotal());
        page.setTotal(total.intValue());
        array.setPageInfo(page);
        return array;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String modifyProductInfo(Product product) {
        String productId = product.getId();
        Product pro = productMapper.selectByPrimaryKey(productId);
        product.setCreateUser(pro.getCreateUser());
        product.setCreateTime(pro.getCreateTime());
        product.setUpdateTime(DateUtil.getCurrentDate());
        product.setUpdateUser(UserContext.getUserSession().getName());
        productMapper.updateByPrimaryKeySelective(product);
        return productId;
    }

    @Override
    public ProductArray queryListByItem(String itemId, Integer pageIndex, Integer pageSize) {
        ProductItem item = productItemService.getItemById(itemId);
        if (item == null) {
            log.info("选择分类不存在");
            return new ProductArray();
        }

        PageHelper.startPage(pageIndex, pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        if (item.getIsRoot().equals(Const.B_ONE)) {
            List<String> idList = productItemService.getAllItemsByParentId(itemId).stream().map(ProductItem::getId).collect(Collectors.toList());
            idList.add(itemId);
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemIdIn(idList);
        } else {
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemIdEqualTo(itemId);
        }
        List<Product> list = productMapper.selectByExample(example);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        ProductArray array = new ProductArray();
        array.setProductList(getDtoList(list));
        cn.chainof.sunup.controller.dto.data.PageInfo page = new cn.chainof.sunup.controller.dto.data.PageInfo();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        Long total = Long.valueOf(pageInfo.getTotal());
        page.setTotal(total.intValue());
        array.setPageInfo(page);
        return array;
    }

    @Override
    public ProductArray queryListByLabel(String labelId, Integer pageIndex, Integer pageSize) {
        ProjectLabel label = projectLabelService.getLabelById(labelId);
        if (label == null) {
            log.info("选择标签不存在");
            return new ProductArray();
        }
        PageHelper.startPage(pageIndex, pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        String labelLike = "%" + labelId + "%";
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andLabelsLike(labelLike);
        List<Product> list = productMapper.selectByExample(example);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        ProductArray array = new ProductArray();
        array.setProductList(getDtoList(list));
        cn.chainof.sunup.controller.dto.data.PageInfo page = new cn.chainof.sunup.controller.dto.data.PageInfo();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        Long total = Long.valueOf(pageInfo.getTotal());
        page.setTotal(total.intValue());
        array.setPageInfo(page);
        return array;
    }

    @Override
    public ProductArray queryListByItemLabel(String itemId, String labelId, Integer pageIndex, Integer pageSize) {

        PageHelper.startPage(pageIndex, pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        ProductExample.Criteria criteria = example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO);
        if (StringUtil.isNotEmpty(itemId)) {
            ProductItem item = productItemService.getItemById(itemId);
            if (item.getIsRoot().equals(Const.B_ONE)) {
                List<String> idList = productItemService.getAllItemsByParentId(itemId).stream().map(ProductItem::getId).collect(Collectors.toList());
                criteria.andItemIdIn(idList);
            } else {
                criteria.andItemIdEqualTo(itemId);
            }
        }
        if (StringUtil.isNotEmpty(labelId)) {
            String like = "%" + labelId + "%";
            criteria.andLabelsLike(like);
        }
        List<Product> list = productMapper.selectByExample(example);
        PageInfo<Product> pageInfo = new PageInfo<>(list);
        ProductArray array = new ProductArray();
        array.setProductList(getDtoList(list));
        cn.chainof.sunup.controller.dto.data.PageInfo page = new cn.chainof.sunup.controller.dto.data.PageInfo();
        page.setPageIndex(pageIndex);
        page.setPageSize(pageSize);
        Long total = Long.valueOf(pageInfo.getTotal());
        page.setTotal(total.intValue());
        array.setPageInfo(page);
        return array;
    }

    @Override
    public String deleteProduct(String id) {
        productMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public List<ProductDTO> getDtoList(List<Product> list) {

        List<ProductItem> itemList = new ArrayList<>();
        List<String> itemIds = list.stream().map(Product::getItemId).collect(Collectors.toList());
        if (itemIds!=null&&itemIds.size()>0) {
            itemList= productItemService.queryListByIds(itemIds);
        }
        Map<String, String> itemMap = itemList.stream().collect(Collectors.toMap(ProductItem::getId, a -> a.getItemName(), (k1, k2) -> k1));
        List<String> labelIds = new ArrayList<>();
        for (Product product:list) {
            List<String> ids = JSON.parseArray(product.getLabels(), String.class);
            labelIds.addAll(ids);
        }
        List<ProjectLabel> labelList = new ArrayList<>();
        if (labelIds.size()>0) {
            labelList = projectLabelService.queryListByIds(labelIds);
        }
        Map<String, String> labelMap = labelList.stream().collect(Collectors.toMap(ProjectLabel::getId, a -> a.getName(), (k1, k2) -> k1));

        List<ProductDTO> dtoList = new ArrayList<>();
        for (Product product:list) {
            ProductDTO dto = getProductDto(product,itemMap,labelMap);
            dtoList.add(dto);
        }
        return dtoList;
    }

    @Override
    public ProductDTO getProductDto(String id) {
        Product product = productMapper.selectByPrimaryKey(id);
        List<ProductItem> itemList = productItemService.queryListByIds(Arrays.asList(product.getItemId()));
        Map<String, String> itemMap = itemList.stream().collect(Collectors.toMap(ProductItem::getId, a -> a.getItemName(), (k1, k2) -> k1));
        List<ProjectLabel> labelList = new ArrayList<>();
        List<String> labels = JSON.parseArray(product.getLabels(), String.class);
        if (labels != null && labels.size() > 0) {
            labelList = projectLabelService.queryListByIds(labels);
        }
        Map<String, String> labelMap = labelList.stream().collect(Collectors.toMap(ProjectLabel::getId, a -> a.getName(), (k1, k2) -> k1));

        ProductDTO dto = getProductDto(product,itemMap,labelMap);
        return dto;
    }

    private ProductDTO getProductDto(Product product, Map<String,String> itemMap,Map<String,String> labelMap){
        ProductDTO dto = AutoConvertUtil.autoConvertTo(product,ProductDTO.class);
        if (StringUtil.isNotEmpty(product.getImgUrls())){
            List<String> imgUrls = JSON.parseArray(product.getImgUrls(), String.class);
            dto.setImgUrls(imgUrls);
        }
        if (StringUtil.isNotEmpty(product.getLabels())){
            List<String> labels = JSON.parseArray(product.getLabels(), String.class);
            dto.setLabels(labels);
        }
        if (product.getCreateTime() != null) {
            dto.setCreateTime(DateUtil.getDateStr(product.getCreateTime()));
        }
        if (product.getUpdateTime()!=null) {
            dto.setUpdateTime(DateUtil.getDateStr(product.getUpdateTime()));
        }
        dto.setItemName(itemMap.get(product.getItemId()));
        List<String> labeNames = new ArrayList<>();
        for (String label:dto.getLabels()) {
            labeNames.add(labelMap.get(label));
        }
        dto.setLabelNames(labeNames);
        return dto;
    }
}
