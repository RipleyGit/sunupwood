package cn.chainof.sunup.service;

import cn.chainof.sunup.controller.dto.data.ProductDTO;
import cn.chainof.sunup.model.Product;

import java.util.List;

public interface ProductService {
    String addProductInfo(Product product);

    List<String> deleteProducts(List<String> ids);

    List<Product> queryProducts(String key, Integer pageIndex, Integer pageSize);

    String modifyProductInfo(Product product);

    List<Product> queryListByItem(String itemId, Integer pageIndex, Integer pageSize);

    List<Product> queryListByLabel(String labelId, Integer pageIndex, Integer pageSize);

    List<Product> queryListByItemLabel(String itemId, String labelId, Integer pageIndex, Integer pageSize);

    Product getProductById(String id);

    String deleteProduct(String id);

    ProductDTO getDto(Product product);

    List<ProductDTO> getDtoList(List<Product> list);
}
