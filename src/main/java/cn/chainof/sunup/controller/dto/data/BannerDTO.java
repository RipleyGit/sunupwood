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
 * BannerDTO
 */
@Validated

public class BannerDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("imgUrl")
  private String imgUrl = null;

  @JsonProperty("rank")
  private Integer rank = null;

  public BannerDTO id(String id) {
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

  public BannerDTO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 标题描述
   * @return title
  **/
  @ApiModelProperty(value = "标题描述")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public BannerDTO imgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
    return this;
  }

  /**
   * 图片地址
   * @return imgUrl
  **/
  @ApiModelProperty(value = "图片地址")


  public String getImgUrl() {
    return imgUrl;
  }

  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public BannerDTO rank(Integer rank) {
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


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    BannerDTO bannerDTO = (BannerDTO) o;
    return Objects.equals(this.id, bannerDTO.id) &&
        Objects.equals(this.title, bannerDTO.title) &&
        Objects.equals(this.imgUrl, bannerDTO.imgUrl) &&
        Objects.equals(this.rank, bannerDTO.rank);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, imgUrl, rank);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class BannerDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    imgUrl: ").append(toIndentedString(imgUrl)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
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

