package cn.chainof.sunup.controller.dto.data;

import java.util.Objects;
import cn.chainof.sunup.controller.dto.data.PageInfo;
import cn.chainof.sunup.controller.dto.data.ProductDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.ArrayList;
import java.util.List;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProductArray
 */
@Validated

public class ProductArray   {
  @JsonProperty("productList")
  @Valid
  private List<ProductDTO> productList = null;

  @JsonProperty("pageInfo")
  private PageInfo pageInfo = null;

  public ProductArray productList(List<ProductDTO> productList) {
    this.productList = productList;
    return this;
  }

  public ProductArray addProductListItem(ProductDTO productListItem) {
    if (this.productList == null) {
      this.productList = new ArrayList<ProductDTO>();
    }
    this.productList.add(productListItem);
    return this;
  }

  /**
   * 商品列表
   * @return productList
  **/
  @ApiModelProperty(value = "商品列表")

  @Valid

  public List<ProductDTO> getProductList() {
    return productList;
  }

  public void setProductList(List<ProductDTO> productList) {
    this.productList = productList;
  }

  public ProductArray pageInfo(PageInfo pageInfo) {
    this.pageInfo = pageInfo;
    return this;
  }

  /**
   * Get pageInfo
   * @return pageInfo
  **/
  @ApiModelProperty(value = "")

  @Valid

  public PageInfo getPageInfo() {
    return pageInfo;
  }

  public void setPageInfo(PageInfo pageInfo) {
    this.pageInfo = pageInfo;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductArray productArray = (ProductArray) o;
    return Objects.equals(this.productList, productArray.productList) &&
        Objects.equals(this.pageInfo, productArray.pageInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(productList, pageInfo);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductArray {\n");
    
    sb.append("    productList: ").append(toIndentedString(productList)).append("\n");
    sb.append("    pageInfo: ").append(toIndentedString(pageInfo)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

