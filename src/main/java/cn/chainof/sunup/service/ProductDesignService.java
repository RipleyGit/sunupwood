package cn.chainof.sunup.service;

import cn.chainof.sunup.controller.dto.data.ProductDesignDTO;
import cn.chainof.sunup.controller.dto.data.ProductDesignItemDTO;

import java.util.List;

public interface ProductDesignService {
    String addProductDesign(ProductDesignDTO productDto);

    ProductDesignDTO getProductDesignDTOInfo(String id);

    List<ProductDesignDTO> queryProductList(Integer pageIndex, Integer pageSize);

    String updateProductDesign(ProductDesignDTO designDto);

    String deleteById(String id);

    String addProductDesignItem(ProductDesignItemDTO productItemDto);

    ProductDesignItemDTO getProductDesignItemDTOInfo(String id);

    String updateProductDesignItem(ProductDesignItemDTO designDto);

    List<ProductDesignItemDTO> queryProductItemList(Integer pageIndex, Integer pageSize);

    List<ProductDesignDTO> queryProductListByItem(String itemId, Integer pageIndex, Integer pageSize);
}
