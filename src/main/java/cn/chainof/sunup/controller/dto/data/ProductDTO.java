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
 * ProductDTO
 */
@Validated

public class ProductDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("price")
  private String price = null;

  @JsonProperty("sales")
  private String sales = null;

  @JsonProperty("amount")
  private String amount = null;

  @JsonProperty("measure")
  private String measure = null;

  @JsonProperty("texture")
  private String texture = null;

  @JsonProperty("productDeclare")
  private String productDeclare = null;

  @JsonProperty("imgUrls")
  @Valid
  private List<String> imgUrls = null;

  @JsonProperty("taobaoLink")
  private String taobaoLink = null;

  @JsonProperty("itemId")
  private String itemId = null;

  @JsonProperty("labels")
  @Valid
  private List<String> labels = null;

  @JsonProperty("createUser")
  private String createUser = null;

  @JsonProperty("updateUser")
  private String updateUser = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("updateTime")
  private String updateTime = null;

  public ProductDTO id(String id) {
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

  public ProductDTO type(String type) {
    this.type = type;
    return this;
  }

  /**
   * 商品类型：1 普通商品，2整装商品
   * @return type
  **/
  @ApiModelProperty(value = "商品类型：1 普通商品，2整装商品")


  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public ProductDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 商品名
   * @return name
  **/
  @ApiModelProperty(value = "商品名")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public ProductDTO price(String price) {
    this.price = price;
    return this;
  }

  /**
   * 价格
   * @return price
  **/
  @ApiModelProperty(value = "价格")


  public String getPrice() {
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public ProductDTO sales(String sales) {
    this.sales = sales;
    return this;
  }

  /**
   * 销售量
   * @return sales
  **/
  @ApiModelProperty(value = "销售量")


  public String getSales() {
    return sales;
  }

  public void setSales(String sales) {
    this.sales = sales;
  }

  public ProductDTO amount(String amount) {
    this.amount = amount;
    return this;
  }

  /**
   * 数量
   * @return amount
  **/
  @ApiModelProperty(value = "数量")


  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }

  public ProductDTO measure(String measure) {
    this.measure = measure;
    return this;
  }

  /**
   * 尺寸
   * @return measure
  **/
  @ApiModelProperty(value = "尺寸")


  public String getMeasure() {
    return measure;
  }

  public void setMeasure(String measure) {
    this.measure = measure;
  }

  public ProductDTO texture(String texture) {
    this.texture = texture;
    return this;
  }

  /**
   * 材质，当商品为整装时，为整体特色
   * @return texture
  **/
  @ApiModelProperty(value = "材质，当商品为整装时，为整体特色")


  public String getTexture() {
    return texture;
  }

  public void setTexture(String texture) {
    this.texture = texture;
  }

  public ProductDTO productDeclare(String productDeclare) {
    this.productDeclare = productDeclare;
    return this;
  }

  /**
   * 商品描述
   * @return productDeclare
  **/
  @ApiModelProperty(value = "商品描述")


  public String getProductDeclare() {
    return productDeclare;
  }

  public void setProductDeclare(String productDeclare) {
    this.productDeclare = productDeclare;
  }

  public ProductDTO imgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
    return this;
  }

  public ProductDTO addImgUrlsItem(String imgUrlsItem) {
    if (this.imgUrls == null) {
      this.imgUrls = new ArrayList<String>();
    }
    this.imgUrls.add(imgUrlsItem);
    return this;
  }

  /**
   * 图片列表
   * @return imgUrls
  **/
  @ApiModelProperty(value = "图片列表")


  public List<String> getImgUrls() {
    return imgUrls;
  }

  public void setImgUrls(List<String> imgUrls) {
    this.imgUrls = imgUrls;
  }

  public ProductDTO taobaoLink(String taobaoLink) {
    this.taobaoLink = taobaoLink;
    return this;
  }

  /**
   * 淘宝链接
   * @return taobaoLink
  **/
  @ApiModelProperty(value = "淘宝链接")


  public String getTaobaoLink() {
    return taobaoLink;
  }

  public void setTaobaoLink(String taobaoLink) {
    this.taobaoLink = taobaoLink;
  }

  public ProductDTO itemId(String itemId) {
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

  public ProductDTO labels(List<String> labels) {
    this.labels = labels;
    return this;
  }

  public ProductDTO addLabelsItem(String labelsItem) {
    if (this.labels == null) {
      this.labels = new ArrayList<String>();
    }
    this.labels.add(labelsItem);
    return this;
  }

  /**
   * 标签列表
   * @return labels
  **/
  @ApiModelProperty(value = "标签列表")


  public List<String> getLabels() {
    return labels;
  }

  public void setLabels(List<String> labels) {
    this.labels = labels;
  }

  public ProductDTO createUser(String createUser) {
    this.createUser = createUser;
    return this;
  }

  /**
   * 创建人
   * @return createUser
  **/
  @ApiModelProperty(value = "创建人")


  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public ProductDTO updateUser(String updateUser) {
    this.updateUser = updateUser;
    return this;
  }

  /**
   * 更新人
   * @return updateUser
  **/
  @ApiModelProperty(value = "更新人")


  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public ProductDTO createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * 创建时间
   * @return createTime
  **/
  @ApiModelProperty(value = "创建时间")


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public ProductDTO updateTime(String updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * 更新时间
   * @return updateTime
  **/
  @ApiModelProperty(value = "更新时间")


  public String getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(String updateTime) {
    this.updateTime = updateTime;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ProductDTO productDTO = (ProductDTO) o;
    return Objects.equals(this.id, productDTO.id) &&
        Objects.equals(this.type, productDTO.type) &&
        Objects.equals(this.name, productDTO.name) &&
        Objects.equals(this.price, productDTO.price) &&
        Objects.equals(this.sales, productDTO.sales) &&
        Objects.equals(this.amount, productDTO.amount) &&
        Objects.equals(this.measure, productDTO.measure) &&
        Objects.equals(this.texture, productDTO.texture) &&
        Objects.equals(this.productDeclare, productDTO.productDeclare) &&
        Objects.equals(this.imgUrls, productDTO.imgUrls) &&
        Objects.equals(this.taobaoLink, productDTO.taobaoLink) &&
        Objects.equals(this.itemId, productDTO.itemId) &&
        Objects.equals(this.labels, productDTO.labels) &&
        Objects.equals(this.createUser, productDTO.createUser) &&
        Objects.equals(this.updateUser, productDTO.updateUser) &&
        Objects.equals(this.createTime, productDTO.createTime) &&
        Objects.equals(this.updateTime, productDTO.updateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, type, name, price, sales, amount, measure, texture, productDeclare, imgUrls, taobaoLink, itemId, labels, createUser, updateUser, createTime, updateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class ProductDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    price: ").append(toIndentedString(price)).append("\n");
    sb.append("    sales: ").append(toIndentedString(sales)).append("\n");
    sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
    sb.append("    measure: ").append(toIndentedString(measure)).append("\n");
    sb.append("    texture: ").append(toIndentedString(texture)).append("\n");
    sb.append("    productDeclare: ").append(toIndentedString(productDeclare)).append("\n");
    sb.append("    imgUrls: ").append(toIndentedString(imgUrls)).append("\n");
    sb.append("    taobaoLink: ").append(toIndentedString(taobaoLink)).append("\n");
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    labels: ").append(toIndentedString(labels)).append("\n");
    sb.append("    createUser: ").append(toIndentedString(createUser)).append("\n");
    sb.append("    updateUser: ").append(toIndentedString(updateUser)).append("\n");
    sb.append("    createTime: ").append(toIndentedString(createTime)).append("\n");
    sb.append("    updateTime: ").append(toIndentedString(updateTime)).append("\n");
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

