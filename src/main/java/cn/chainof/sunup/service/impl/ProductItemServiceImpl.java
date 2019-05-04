package cn.chainof.sunup.service.impl;


import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.mapper.ProductItemMapper;
import cn.chainof.sunup.model.ProductItem;
import cn.chainof.sunup.model.ProductItemExample;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.service.ProductItemService;
import cn.chainof.sunup.utils.DateUtil;
import cn.chainof.sunup.utils.KeyUtil;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductItemServiceImpl implements ProductItemService {

    @Autowired
    private ProductItemMapper productItemMapper;

    @Override
    public ProductItem getItemByName(String itemName,String parentId) {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemNameEqualTo(itemName).andItemParentEqualTo(parentId);
        List<ProductItem> itemList = productItemMapper.selectByExample(example);
        if (itemList == null || itemList.size()<1){
            return null;
        }else {
            return itemList.get(0);
        }
    }

    @Override
    public String addItem(ProductItem newItem) {
        ProjectUser user = UserContext.getUserSession();
        if (newItem.getRank()==null){
            ProductItemExample example = new ProductItemExample();
            example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemParentEqualTo(newItem.getItemParent());
            List<ProductItem> itemList = productItemMapper.selectByExample(example);
            Integer max = itemList.stream().map(ProductItem::getRank).max(Integer::compareTo).get();
            newItem.setRank(max+1);
        }
        String itemId = String.valueOf(KeyUtil.genUniqueKey());
        newItem.setId(itemId);
        newItem.setCreateTime(DateUtil.getCurrentDate());
        newItem.setCreateUser(user.getName());
        newItem.setIsDeleted(Const.IS_NORMAL);
        productItemMapper.insertSelective(newItem);
        return itemId;
    }

    @Override
    public ProductItem getItemById(String id) {
        return productItemMapper.selectByPrimaryKey(id);
    }

    @Override
    public String updateItem(ProductItem updateItem) {
        ProjectUser user = UserContext.getUserSession();
        if (updateItem.getRank()==null){
            ProductItemExample example = new ProductItemExample();
            example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemParentEqualTo(updateItem.getItemParent());
            List<ProductItem> itemList = productItemMapper.selectByExample(example);
            Integer max = itemList.stream().map(ProductItem::getRank).max(Integer::compareTo).get();
            updateItem.setRank(max+1);
        }
        updateItem.setUpdateTime(DateUtil.getCurrentDate());
        updateItem.setUpdateUser(user.getName());
        productItemMapper.updateByPrimaryKeySelective(updateItem);
        return updateItem.getId();
    }

    @Override
    public String deletedItem(String id) {
        ProductItem productItem = productItemMapper.selectByPrimaryKey(id);
        productItem.setIsDeleted(Const.IS_DELETED);
        productItem.setUpdateTime(DateUtil.getCurrentDate());
        productItem.setUpdateUser(UserContext.getUserSession().getName());
        productItemMapper.updateByPrimaryKeySelective(productItem);
        return id;
    }

    @Override
    public List<ProductItem> queryListByKey(String key) {
        List<ProductItem> list = new ArrayList<>();
        ProductItemExample example = new ProductItemExample();
        if (StringUtil.isNotEmpty(key)){
            key = "%"+key+"%";
            example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemNameLike(key);
            List<ProductItem> listByName = productItemMapper.selectByExample(example);
            list.addAll(listByName);
            example = new ProductItemExample();
            example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemDeclareLike(key);
            List<ProductItem> listByDeclare = productItemMapper.selectByExample(example);
            list.addAll(listByDeclare);
        }else {
            example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL);
            list = productItemMapper.selectByExample(example);
        }
        return list;
    }

    @Override
    public List<ProductItem> getRootItems() {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andIsRootEqualTo(Const.IS_NORMAL);
        return productItemMapper.selectByExample(example);
    }

    @Override
    public List<ProductItem> getItemsByParentId(String parentId) {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.IS_NORMAL).andItemParentEqualTo(parentId);
        return productItemMapper.selectByExample(example);
    }
}