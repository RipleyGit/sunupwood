package cn.chainof.sunup.controller.dto.data;

import java.util.Objects;
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
 * ProductDesignDTO
 */
@Validated

public class ProductDesignDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("itemId")
  private String itemId = null;

  @JsonProperty("itemName")
  private String itemName = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rank")
  private Integer rank = null;

  @JsonProperty("egName")
  private String egName = null;

  @JsonProperty("lordImg")
  private String lordImg = null;

  @JsonProperty("isLarge")
  private Integer isLarge = null;

  @JsonProperty("imgUrls")
  @Valid
  private List<String> imgUrls = null;

  public ProductDesignDTO id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  **/
  @ApiModelProperty(value = "")


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ProductDesignDTO itemId(String itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * 分类ID
   * @return itemId
  **/
  @ApiModelProperty(value = "分类ID")


  public String getItemId() {
    return itemId;
  }

  public void setItemId(String itemId) {
    this.itemId = itemId;
  }

  public ProductDesignDTO itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  /**
   * 分类名称
   * @return itemName
  **/
  @ApiModelProperty(value = "分类名称")


  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public ProductDesignDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 中文描述
   * @return name
  **/
  @ApiModelProperty(value = "中文描述")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductDesignDTO rank(Integer rank) {
    this.rank = rank;
    return this;
  }

  /**
   * 排序
   * @return rank
  **/
  @ApiModelProperty(value = "排序")


  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  public ProductDesignDTO egName(String egName) {
    this.egName = egName;
    return this;
  }

  /**
   * 英文描述
   * @return egName
  **/
  @ApiModelProperty(value = "英文描述")


  public String getEgName() {
    return egName;
  }

  public void setEgName(String egName) {
    this.egName = egName;
  }

  public ProductDesignDTO lordImg(String lordImg) {
    this.lordImg = lordImg;
    return this;
  }

  /**
   * 主图
   * @return lordImg
  **/
  @ApiModelProperty(value = "主图")


  public String getLordImg() {
    return lordImg;
  }

  public void setLordImg(String lordImg) {
    this.lordImg = lordImg;
  }

  public ProductDesignDTO isLarge(Integer isLarge) {
    this.isLarge = isLarge;
    return this;
  }

  /**
   * 是否是大图
   * @return isLarge
  **/
  @ApiModelProperty(value = "是否是大图")


  public Integer getIsLarge() {
    return isLarge;
  }

  public void setIsLarge(Integer isLarge) {
    this.isLarge = isLarge;
  }

  public ProductDesignDTO imgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
    return this;
  }

  public ProductDesignDTO addImgUrlsItem(String imgUrlsItem) {
    if (this.imgUrls == null) {
      this.imgUrls = new ArrayList<String>();
    }
    this.imgUrls.add(imgUrlsItem);
    return this;
  }

  /**
   * Get imgUrls
   * @return imgUrls
  **/
  @ApiModelProperty(value = "")


  public List<String> getImgUrls() {
    return imgUrls;
  }

  public void setImgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDesignDTO productDesignDTO = (ProductDesignDTO) o;
    return Objects.equals(this.id, productDesignDTO.id) &&
        Objects.equals(this.itemId, productDesignDTO.itemId) &&
        Objects.equals(this.itemName, productDesignDTO.itemName) &&
        Objects.equals(this.name, productDesignDTO.name) &&
        Objects.equals(this.rank, productDesignDTO.rank) &&
        Objects.equals(this.egName, productDesignDTO.egName) &&
        Objects.equals(this.lordImg, productDesignDTO.lordImg) &&
        Objects.equals(this.isLarge, productDesignDTO.isLarge) &&
        Objects.equals(this.imgUrls, productDesignDTO.imgUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, itemId, itemName, name, rank, egName, lordImg, isLarge, imgUrls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDesignDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    itemName: ").append(toIndentedString(itemName)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    egName: ").append(toIndentedString(egName)).append("\n");
    sb.append("    lordImg: ").append(toIndentedString(lordImg)).append("\n");
    sb.append("    isLarge: ").append(toIndentedString(isLarge)).append("\n");
    sb.append("    imgUrls: ").append(toIndentedString(imgUrls)).append("\n");
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

