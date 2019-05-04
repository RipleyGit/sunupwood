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
 * LabelDTO
 */
@Validated

public class LabelDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("labelCode")
  private String labelCode = null;

  @JsonProperty("labelDeclare")
  private String labelDeclare = null;

  @JsonProperty("labelImg")
  private String labelImg = null;

  public LabelDTO id(String id) {
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

  public LabelDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 标签名
   * @return name
  **/
  @ApiModelProperty(value = "标签名")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public LabelDTO labelCode(String labelCode) {
    this.labelCode = labelCode;
    return this;
  }

  /**
   * 标签代码，全系统唯一，且不可改
   * @return labelCode
  **/
  @ApiModelProperty(value = "标签代码，全系统唯一，且不可改")


  public String getLabelCode() {
    return labelCode;
  }

  public void setLabelCode(String labelCode) {
    this.labelCode = labelCode;
  }

  public LabelDTO labelDeclare(String labelDeclare) {
    this.labelDeclare = labelDeclare;
    return this;
  }

  /**
   * 描述标签
   * @return labelDeclare
  **/
  @ApiModelProperty(value = "描述标签")


  public String getLabelDeclare() {
    return labelDeclare;
  }

  public void setLabelDeclare(String labelDeclare) {
    this.labelDeclare = labelDeclare;
  }

  public LabelDTO labelImg(String labelImg) {
    this.labelImg = labelImg;
    return this;
  }

  /**
   * 标签图片，暂不使用
   * @return labelImg
  **/
  @ApiModelProperty(value = "标签图片，暂不使用")


  public String getLabelImg() {
    return labelImg;
  }

  public void setLabelImg(String labelImg) {
    this.labelImg = labelImg;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LabelDTO labelDTO = (LabelDTO) o;
    return Objects.equals(this.id, labelDTO.id) &&
        Objects.equals(this.name, labelDTO.name) &&
        Objects.equals(this.labelCode, labelDTO.labelCode) &&
        Objects.equals(this.labelDeclare, labelDTO.labelDeclare) &&
        Objects.equals(this.labelImg, labelDTO.labelImg);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, labelCode, labelDeclare, labelImg);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LabelDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    labelCode: ").append(toIndentedString(labelCode)).append("\n");
    sb.append("    labelDeclare: ").append(toIndentedString(labelDeclare)).append("\n");
    sb.append("    labelImg: ").append(toIndentedString(labelImg)).append("\n");
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

