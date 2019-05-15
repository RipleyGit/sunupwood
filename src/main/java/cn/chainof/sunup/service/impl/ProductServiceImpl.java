package cn.chainof.sunup.service.impl;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.mapper.ProductMapper;
import cn.chainof.sunup.model.Product;
import cn.chainof.sunup.model.ProductExample;
import cn.chainof.sunup.model.ProductItem;
import cn.chainof.sunup.model.ProjectLabel;
import cn.chainof.sunup.service.ProductItemService;
import cn.chainof.sunup.service.ProductService;
import cn.chainof.sunup.service.ProjectLabelService;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
        product.setIsDeleted(Const.IS_NORMAL);
        if (StringUtil.isEmpty(product.getImgUrls())){
            product.setImgUrls("{}");
        }
        if (StringUtil.isEmpty(product.getLabels())){
            product.setLabels("{}");
        }
        product.setCreateTime(DateUtil.getCurrentDate());
        product.setCreateUser(UserContext.getUserSession().getName());
        productMapper.insertSelective(product);
        return productId;
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public List<String> deleteProducts(List<String> ids) {
        ProductExample example = new ProductExample();
        example.createCriteria().andIdIn(ids);
        List<Product> list = productMapper.selectByExample(example);
        for (Product pro:list) {
            pro.setIsDeleted(Const.IS_DELETED);
            productMapper.insertSelective(pro);
        }
        return list.stream().map(Product::getId).collect(Collectors.toList());
    }

    @Override
    public List<Product> queryProducts(String key, Integer pageIndex, Integer pageSize) {

        PageHelper.startPage(pageIndex,pageSize);

        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        if (StringUtil.isNotEmpty(key)){
            String likeKey = "%" + key + "%";
            ProductExample.Criteria nameLike = example.createCriteria().andNameLike(likeKey);
            example.or(nameLike);
            ProductExample.Criteria declareLike = example.createCriteria().andProductDeclareLike(likeKey);
            example.or(declareLike);
            ProductExample.Criteria priceLike = example.createCriteria().andPriceLike(likeKey);
            example.or(priceLike);
            ProductExample.Criteria amountLike = example.createCriteria().andAmountLike(likeKey);
            example.or(amountLike);
            ProductExample.Criteria measureLike = example.createCriteria().andMeasureLike(likeKey);
            example.or(measureLike);
            ProductExample.Criteria salesLike = example.createCriteria().andSalesLike(likeKey);
            example.or(salesLike);
            ProductExample.Criteria textureLike = example.createCriteria().andTextureLike(likeKey);
            example.or(textureLike);
            ProductExample.Criteria userLike = example.createCriteria().andCreateUserLike(likeKey);
            example.or(userLike);
            ProductExample.Criteria updateUserLike = example.createCriteria().andUpdateUserLike(likeKey);
            example.or(updateUserLike);
        }
        return productMapper.selectByExample(example);
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
    public List<Product> queryListByItem(String itemId, Integer pageIndex, Integer pageSize) {
        List<Product> list = new ArrayList<>();
        ProductItem item = productItemService.getItemById(itemId);
        if (item == null){
            log.info("选择分类不存在");
            return list;
        }
        PageHelper.startPage(pageIndex,pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemIdEqualTo(itemId);
        list = productMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Product> queryListByLabel(String labelId, Integer pageIndex, Integer pageSize) {
        List<Product> list = new ArrayList<>();
        ProjectLabel label = projectLabelService.getLabelById(labelId);
        if (label == null){
            log.info("选择标签不存在");
            return list;
        }
        PageHelper.startPage(pageIndex,pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        String labelLike = "%" + labelId + "%";
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andLabelsLike(labelLike);
        list = productMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<Product> queryListByItemLabel(String itemId, String labelId, Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        ProductExample example = new ProductExample();
        example.setOrderByClause("update_time DESC");
        ProductExample.Criteria criteria = example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL);
        if (StringUtil.isNotEmpty(itemId)){
            criteria.andItemIdEqualTo(itemId);
        }
        if (StringUtil.isNotEmpty(labelId)){
            String like = "%" + labelId + "%";
            criteria.andLabelsLike(like);
        }
        List<Product>  list= productMapper.selectByExample(example);
        return list;
    }

    @Override
    public Product getProductById(String id) {
        return productMapper.selectByPrimaryKey(id);
    }
}
