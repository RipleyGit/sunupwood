package cn.chainof.sunup.service;

import cn.chainof.sunup.controller.dto.data.ProductDesignDTO;

import java.util.List;

public interface ProductDesignService {
    String addProductDesign(ProductDesignDTO productDto);

    ProductDesignDTO getProductDesignDTOInfo(String id);

    List<ProductDesignDTO> queryProductList(Integer pageIndex, Integer pageSize);

    String updateProductDesign(ProductDesignDTO designDto);

    String deleteById(String id);
}
