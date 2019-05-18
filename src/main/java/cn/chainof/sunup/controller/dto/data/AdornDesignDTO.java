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
 * AdornDesignDTO
 */
@Validated

public class AdornDesignDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rank")
  private Integer rank = null;

  @JsonProperty("lordImg")
  private String lordImg = null;

  @JsonProperty("imgUrls")
  @Valid
  private List<String> imgUrls = null;

  public AdornDesignDTO id(String id) {
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

  public AdornDesignDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 描述
   * @return name
  **/
  @ApiModelProperty(value = "描述")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public AdornDesignDTO rank(Integer rank) {
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

  public AdornDesignDTO lordImg(String lordImg) {
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

  public AdornDesignDTO imgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
    return this;
  }

  public AdornDesignDTO addImgUrlsItem(String imgUrlsItem) {
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
    AdornDesignDTO adornDesignDTO = (AdornDesignDTO) o;
    return Objects.equals(this.id, adornDesignDTO.id) &&
        Objects.equals(this.name, adornDesignDTO.name) &&
        Objects.equals(this.rank, adornDesignDTO.rank) &&
        Objects.equals(this.lordImg, adornDesignDTO.lordImg) &&
        Objects.equals(this.imgUrls, adornDesignDTO.imgUrls);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, rank, lordImg, imgUrls);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdornDesignDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    lordImg: ").append(toIndentedString(lordImg)).append("\n");
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

