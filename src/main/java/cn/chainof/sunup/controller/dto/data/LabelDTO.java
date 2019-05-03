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
   * Get name
   * @return name
  **/
  @ApiModelProperty(value = "")


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
   * Get labelCode
   * @return labelCode
  **/
  @ApiModelProperty(value = "")


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
   * Get labelDeclare
   * @return labelDeclare
  **/
  @ApiModelProperty(value = "")


  public String getLabelDeclare() {
    return labelDeclare;
  }

  public void setLabelDeclare(String labelDeclare) {
    this.labelDeclare = labelDeclare;
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
        Objects.equals(this.labelDeclare, labelDTO.labelDeclare);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, labelCode, labelDeclare);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class LabelDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    labelCode: ").append(toIndentedString(labelCode)).append("\n");
    sb.append("    labelDeclare: ").append(toIndentedString(labelDeclare)).append("\n");
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

