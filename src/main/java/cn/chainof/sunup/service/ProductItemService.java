package cn.chainof.sunup.service;

import cn.chainof.sunup.model.ProductItem;

import java.util.List;

public interface ProductItemService {
    ProductItem getItemByName(String itemName,String parentId);

    String addItem(ProductItem newItem);

    ProductItem getItemById(String id);

    String updateItem(ProductItem updateItem);

    String deletedItem(String id);

    List<ProductItem> queryListByKey(String key);

    List<ProductItem> getRootItems();

    List<ProductItem> getItemsByParentId(String parentId);

    List<ProductItem> getAllItemsByParentId(String parentId);

    List<ProductItem> queryListByIds(List<String> asList);

    List<ProductItem> queryListByName(String itemName);
}
