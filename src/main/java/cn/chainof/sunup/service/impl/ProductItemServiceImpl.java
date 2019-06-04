package cn.chainof.sunup.service.impl;


import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.constant.Const;
import cn.chainof.sunup.exception.ClientException;
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
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductItemServiceImpl implements ProductItemService {

    @Autowired
    private ProductItemMapper productItemMapper;

    @Override
    public ProductItem getItemByName(String itemName,String parentId) {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemNameEqualTo(itemName).andItemParentEqualTo(parentId);
        List<ProductItem> itemList = productItemMapper.selectByExample(example);
        if (itemList == null || itemList.size()<1){
            return null;
        }else {
            return itemList.get(0);
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String addItem(ProductItem newItem) {
        ProjectUser user = UserContext.getUserSession();
        if (newItem.getRank()==null){
            ProductItemExample example = new ProductItemExample();
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemParentEqualTo(newItem.getItemParent());
            List<ProductItem> itemList = productItemMapper.selectByExample(example);
            Integer max = itemList.stream().map(ProductItem::getRank).max(Integer::compareTo).get();
            newItem.setRank(max+1);
        }
        String itemId = String.valueOf(KeyUtil.genUniqueKey());
        newItem.setId(itemId);
        newItem.setCreateTime(DateUtil.getCurrentDate());
        newItem.setCreateUser(user.getName());
        newItem.setIsDeleted(Const.B_ZERO);
        productItemMapper.insertSelective(newItem);
        return itemId;
    }

    @Override
    public ProductItem getItemById(String id) {
        return productItemMapper.selectByPrimaryKey(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String updateItem(ProductItem updateItem) {
        ProductItemExample itemExample = new ProductItemExample();
        itemExample.createCriteria().andIsDeletedEqualTo(Const.B_ZERO)
                .andItemNameEqualTo(updateItem.getItemName())
                .andIdNotEqualTo(updateItem.getId());
        List<ProductItem> list = productItemMapper.selectByExample(itemExample);
        if (list != null && list.size()>0){
            throw new ClientException("该分类已存在");
        }

        ProjectUser user = UserContext.getUserSession();
        if (updateItem.getRank()==null){
            ProductItemExample example = new ProductItemExample();
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemParentEqualTo(updateItem.getItemParent());
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
    @Transactional(rollbackFor = Exception.class, propagation = Propagation.SUPPORTS)
    public String deletedItem(String id) {
        productItemMapper.deleteByPrimaryKey(id);
        return id;
    }

    @Override
    public List<ProductItem> queryListByKey(String key) {
        List<ProductItem> list = new ArrayList<>();
        ProductItemExample example = new ProductItemExample();
        example.setOrderByClause("update_time DESC");
        if (StringUtil.isNotEmpty(key)){
            key = "%"+key+"%";
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemNameLike(key);
            List<ProductItem> listByName = productItemMapper.selectByExample(example);
            list.addAll(listByName);
            example = new ProductItemExample();
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemDeclareLike(key);
            List<ProductItem> listByDeclare = productItemMapper.selectByExample(example);
            list.addAll(listByDeclare);
        }else {
            example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO);
            list = productItemMapper.selectByExample(example);
        }
        return list;
    }

    @Override
    public List<ProductItem> getRootItems() {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andIsRootEqualTo(Const.I_ONE.byteValue());
        return productItemMapper.selectByExample(example);
    }

    @Override
    public List<ProductItem> getItemsByParentId(String parentId) {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andItemParentEqualTo(parentId);

        HashSet<ProductItem> list = getParentItem(null,parentId);
        return list.stream().collect(Collectors.toList());
    }


    public HashSet<ProductItem> getParentItem(HashSet<ProductItem> set, String id){
        if (set == null){
            set = new HashSet<>();
        }
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andItemParentEqualTo(id).andIsDeletedEqualTo(Const.B_ZERO);
        List<ProductItem> itemList = productItemMapper.selectByExample(example);
        for (ProductItem item:itemList) {
            set.add(item);
            getParentItem(set,item.getId());
        }
        return set;

    }

    @Override
    public List<ProductItem> queryListByIds(List<String> asList) {
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andIdIn(asList);
        return productItemMapper.selectByExample(example);
    }

    @Override
    public List<ProductItem> queryListByName(String itemName){
        ProductItemExample example = new ProductItemExample();
        example.createCriteria().andIsDeletedEqualTo(Const.B_ZERO).andItemNameLike("%"+itemName+"%");
        return productItemMapper.selectByExample(example);
    }
}
