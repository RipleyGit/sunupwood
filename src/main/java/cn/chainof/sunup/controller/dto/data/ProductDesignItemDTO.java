package cn.chainof.sunup.controller.dto.data;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * ProductDesignItemDTO
 */
@Validated

public class ProductDesignItemDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rank")
  private Integer rank = null;

  @JsonProperty("egName")
  private String egName = null;

  @JsonProperty("lordImg")
  private String lordImg = null;

  public ProductDesignItemDTO id(String id) {
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

  public ProductDesignItemDTO name(String name) {
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

  public ProductDesignItemDTO rank(Integer rank) {
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

  public ProductDesignItemDTO egName(String egName) {
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

  public ProductDesignItemDTO lordImg(String lordImg) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDesignItemDTO productDesignItemDTO = (ProductDesignItemDTO) o;
    return Objects.equals(this.id, productDesignItemDTO.id) &&
        Objects.equals(this.name, productDesignItemDTO.name) &&
        Objects.equals(this.rank, productDesignItemDTO.rank) &&
        Objects.equals(this.egName, productDesignItemDTO.egName) &&
        Objects.equals(this.lordImg, productDesignItemDTO.lordImg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, rank, egName, lordImg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDesignItemDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    egName: ").append(toIndentedString(egName)).append("\n");
    sb.append("    lordImg: ").append(toIndentedString(lordImg)).append("\n");
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

