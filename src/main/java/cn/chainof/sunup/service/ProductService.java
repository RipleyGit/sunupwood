package cn.chainof.sunup.service;

import cn.chainof.sunup.controller.dto.data.ProductArray;
import cn.chainof.sunup.controller.dto.data.ProductDTO;
import cn.chainof.sunup.model.Product;

import java.util.List;

public interface ProductService {
    String addProductInfo(Product product);

    List<String> deleteProducts(List<String> ids);

    ProductArray queryProducts(String key, Integer pageIndex, Integer pageSize);

    String modifyProductInfo(Product product);

    ProductArray queryListByItem(String itemId, Integer pageIndex, Integer pageSize);

    ProductArray queryListByLabel(String labelId, Integer pageIndex, Integer pageSize);

    ProductArray queryListByItemLabel(String itemId, String labelId, Integer pageIndex, Integer pageSize);

    Product getProductById(String id);

    String deleteProduct(String id);

    ProductDTO getDto(Product product);

    List<ProductDTO> getDtoList(List<Product> list);
}
