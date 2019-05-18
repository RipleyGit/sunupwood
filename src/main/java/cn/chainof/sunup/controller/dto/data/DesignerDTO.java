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
 * DesignerDTO
 */
@Validated

public class DesignerDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("rank")
  private Integer rank = null;

  @JsonProperty("sex")
  private String sex = null;

  @JsonProperty("age")
  private String age = null;

  @JsonProperty("photo")
  private String photo = null;

  @JsonProperty("introduce")
  private String introduce = null;

  @JsonProperty("imgReels")
  @Valid
  private List<String> imgReels = null;

  public DesignerDTO id(String id) {
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

  public DesignerDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 设计师名
   * @return name
  **/
  @ApiModelProperty(value = "设计师名")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public DesignerDTO rank(Integer rank) {
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

  public DesignerDTO sex(String sex) {
    this.sex = sex;
    return this;
  }

  /**
   * 性别
   * @return sex
  **/
  @ApiModelProperty(value = "性别")


  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public DesignerDTO age(String age) {
    this.age = age;
    return this;
  }

  /**
   * 年龄
   * @return age
  **/
  @ApiModelProperty(value = "年龄")


  public String getAge() {
    return age;
  }

  public void setAge(String age) {
    this.age = age;
  }

  public DesignerDTO photo(String photo) {
    this.photo = photo;
    return this;
  }

  /**
   * 个人照片
   * @return photo
  **/
  @ApiModelProperty(value = "个人照片")


  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public DesignerDTO introduce(String introduce) {
    this.introduce = introduce;
    return this;
  }

  /**
   * 个人介绍
   * @return introduce
  **/
  @ApiModelProperty(value = "个人介绍")


  public String getIntroduce() {
    return introduce;
  }

  public void setIntroduce(String introduce) {
    this.introduce = introduce;
  }

  public DesignerDTO imgReels(List<String> imgReels) {
    this.imgReels = imgReels;
    return this;
  }

  public DesignerDTO addImgReelsItem(String imgReelsItem) {
    if (this.imgReels == null) {
      this.imgReels = new ArrayList<String>();
    }
    this.imgReels.add(imgReelsItem);
    return this;
  }

  /**
   * 设计师作品集图片列表
   * @return imgReels
  **/
  @ApiModelProperty(value = "设计师作品集图片列表")


  public List<String> getImgReels() {
    return imgReels;
  }

  public void setImgReels(List<String> imgReels) {
    this.imgReels = imgReels;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DesignerDTO designerDTO = (DesignerDTO) o;
    return Objects.equals(this.id, designerDTO.id) &&
        Objects.equals(this.name, designerDTO.name) &&
        Objects.equals(this.rank, designerDTO.rank) &&
        Objects.equals(this.sex, designerDTO.sex) &&
        Objects.equals(this.age, designerDTO.age) &&
        Objects.equals(this.photo, designerDTO.photo) &&
        Objects.equals(this.introduce, designerDTO.introduce) &&
        Objects.equals(this.imgReels, designerDTO.imgReels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, rank, sex, age, photo, introduce, imgReels);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class DesignerDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    age: ").append(toIndentedString(age)).append("\n");
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    introduce: ").append(toIndentedString(introduce)).append("\n");
    sb.append("    imgReels: ").append(toIndentedString(imgReels)).append("\n");
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

