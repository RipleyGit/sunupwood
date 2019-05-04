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
 * ItemDTO
 */
@Validated

public class ItemDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("rank")
  private Integer rank = null;

  @JsonProperty("itemName")
  private String itemName = null;

  @JsonProperty("itemImg")
  private String itemImg = null;

  @JsonProperty("parentId")
  private String parentId = null;

  @JsonProperty("parentName")
  private String parentName = null;

  @JsonProperty("itemDeclare")
  private String itemDeclare = null;

  @JsonProperty("isRoot")
  private Integer isRoot = null;

  public ItemDTO id(String id) {
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

  public ItemDTO rank(Integer rank) {
    this.rank = rank;
    return this;
  }

  /**
   * 序号
   * @return rank
  **/
  @ApiModelProperty(value = "序号")


  public Integer getRank() {
    return rank;
  }

  public void setRank(Integer rank) {
    this.rank = rank;
  }

  public ItemDTO itemName(String itemName) {
    this.itemName = itemName;
    return this;
  }

  /**
   * 分类名
   * @return itemName
  **/
  @ApiModelProperty(value = "分类名")


  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public ItemDTO itemImg(String itemImg) {
    this.itemImg = itemImg;
    return this;
  }

  /**
   * 分类图片
   * @return itemImg
  **/
  @ApiModelProperty(value = "分类图片")


  public String getItemImg() {
    return itemImg;
  }

  public void setItemImg(String itemImg) {
    this.itemImg = itemImg;
  }

  public ItemDTO parentId(String parentId) {
    this.parentId = parentId;
    return this;
  }

  /**
   * 上级分类ID
   * @return parentId
  **/
  @ApiModelProperty(value = "上级分类ID")


  public String getParentId() {
    return parentId;
  }

  public void setParentId(String parentId) {
    this.parentId = parentId;
  }

  public ItemDTO parentName(String parentName) {
    this.parentName = parentName;
    return this;
  }

  /**
   * 上级分类名
   * @return parentName
  **/
  @ApiModelProperty(value = "上级分类名")


  public String getParentName() {
    return parentName;
  }

  public void setParentName(String parentName) {
    this.parentName = parentName;
  }

  public ItemDTO itemDeclare(String itemDeclare) {
    this.itemDeclare = itemDeclare;
    return this;
  }

  /**
   * 分类描述
   * @return itemDeclare
  **/
  @ApiModelProperty(value = "分类描述")


  public String getItemDeclare() {
    return itemDeclare;
  }

  public void setItemDeclare(String itemDeclare) {
    this.itemDeclare = itemDeclare;
  }

  public ItemDTO isRoot(Integer isRoot) {
    this.isRoot = isRoot;
    return this;
  }

  /**
   * 是否是一级分类
   * @return isRoot
  **/
  @ApiModelProperty(value = "是否是一级分类")


  public Integer getIsRoot() {
    return isRoot;
  }

  public void setIsRoot(Integer isRoot) {
    this.isRoot = isRoot;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ItemDTO itemDTO = (ItemDTO) o;
    return Objects.equals(this.id, itemDTO.id) &&
        Objects.equals(this.rank, itemDTO.rank) &&
        Objects.equals(this.itemName, itemDTO.itemName) &&
        Objects.equals(this.itemImg, itemDTO.itemImg) &&
        Objects.equals(this.parentId, itemDTO.parentId) &&
        Objects.equals(this.parentName, itemDTO.parentName) &&
        Objects.equals(this.itemDeclare, itemDTO.itemDeclare) &&
        Objects.equals(this.isRoot, itemDTO.isRoot);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, rank, itemName, itemImg, parentId, parentName, itemDeclare, isRoot);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ItemDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    rank: ").append(toIndentedString(rank)).append("\n");
    sb.append("    itemName: ").append(toIndentedString(itemName)).append("\n");
    sb.append("    itemImg: ").append(toIndentedString(itemImg)).append("\n");
    sb.append("    parentId: ").append(toIndentedString(parentId)).append("\n");
    sb.append("    parentName: ").append(toIndentedString(parentName)).append("\n");
    sb.append("    itemDeclare: ").append(toIndentedString(itemDeclare)).append("\n");
    sb.append("    isRoot: ").append(toIndentedString(isRoot)).append("\n");
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

