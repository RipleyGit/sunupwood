package cn.chainof.sunup.mapper;

import cn.chainof.sunup.model.ProductItem;
import cn.chainof.sunup.model.ProductItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ProductItemMapper {
    long countByExample(ProductItemExample example);

    int deleteByExample(ProductItemExample example);

    int deleteByPrimaryKey(String id);

    int insert(ProductItem record);

    int insertSelective(ProductItem record);

    List<ProductItem> selectByExample(ProductItemExample example);

    ProductItem selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") ProductItem record, @Param("example") ProductItemExample example);

    int updateByExample(@Param("record") ProductItem record, @Param("example") ProductItemExample example);

    int updateByPrimaryKeySelective(ProductItem record);

    int updateByPrimaryKey(ProductItem record);
}