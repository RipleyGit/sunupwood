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
 * NewsDTO
 */
@Validated

public class NewsDTO   {
  @JsonProperty("id")
  private String id = null;

  @JsonProperty("stye")
  private String stye = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("intro")
  private String intro = null;

  @JsonProperty("author")
  private String author = null;

  @JsonProperty("back_img")
  private String backImg = null;

  @JsonProperty("subject")
  private String subject = null;

  @JsonProperty("createUser")
  private String createUser = null;

  @JsonProperty("updateUser")
  private String updateUser = null;

  @JsonProperty("createTime")
  private String createTime = null;

  @JsonProperty("updateTime")
  private String updateTime = null;

  public NewsDTO id(String id) {
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

  public NewsDTO stye(String stye) {
    this.stye = stye;
    return this;
  }

  /**
   * 新闻类型：1，公司新闻；2 行业新闻
   * @return stye
  **/
  @ApiModelProperty(value = "新闻类型：1，公司新闻；2 行业新闻")


  public String getStye() {
    return stye;
  }

  public void setStye(String stye) {
    this.stye = stye;
  }

  public NewsDTO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 新闻标题
   * @return title
  **/
  @ApiModelProperty(value = "新闻标题")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public NewsDTO intro(String intro) {
    this.intro = intro;
    return this;
  }

  /**
   * 新闻简介
   * @return intro
  **/
  @ApiModelProperty(value = "新闻简介")


  public String getIntro() {
    return intro;
  }

  public void setIntro(String intro) {
    this.intro = intro;
  }

  public NewsDTO author(String author) {
    this.author = author;
    return this;
  }

  /**
   * 作者
   * @return author
  **/
  @ApiModelProperty(value = "作者")


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public NewsDTO backImg(String backImg) {
    this.backImg = backImg;
    return this;
  }

  /**
   * 背景图片
   * @return backImg
  **/
  @ApiModelProperty(value = "背景图片")


  public String getBackImg() {
    return backImg;
  }

  public void setBackImg(String backImg) {
    this.backImg = backImg;
  }

  public NewsDTO subject(String subject) {
    this.subject = subject;
    return this;
  }

  /**
   * 新闻内容
   * @return subject
  **/
  @ApiModelProperty(value = "新闻内容")


  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public NewsDTO createUser(String createUser) {
    this.createUser = createUser;
    return this;
  }

  /**
   * Get createUser
   * @return createUser
  **/
  @ApiModelProperty(value = "")


  public String getCreateUser() {
    return createUser;
  }

  public void setCreateUser(String createUser) {
    this.createUser = createUser;
  }

  public NewsDTO updateUser(String updateUser) {
    this.updateUser = updateUser;
    return this;
  }

  /**
   * Get updateUser
   * @return updateUser
  **/
  @ApiModelProperty(value = "")


  public String getUpdateUser() {
    return updateUser;
  }

  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser;
  }

  public NewsDTO createTime(String createTime) {
    this.createTime = createTime;
    return this;
  }

  /**
   * Get createTime
   * @return createTime
  **/
  @ApiModelProperty(value = "")


  public String getCreateTime() {
    return createTime;
  }

  public void setCreateTime(String createTime) {
    this.createTime = createTime;
  }

  public NewsDTO updateTime(String updateTime) {
    this.updateTime = updateTime;
    return this;
  }

  /**
   * Get updateTime
   * @return updateTime
  **/
  @ApiModelProperty(value = "")


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
    NewsDTO newsDTO = (NewsDTO) o;
    return Objects.equals(this.id, newsDTO.id) &&
        Objects.equals(this.stye, newsDTO.stye) &&
        Objects.equals(this.title, newsDTO.title) &&
        Objects.equals(this.intro, newsDTO.intro) &&
        Objects.equals(this.author, newsDTO.author) &&
        Objects.equals(this.backImg, newsDTO.backImg) &&
        Objects.equals(this.subject, newsDTO.subject) &&
        Objects.equals(this.createUser, newsDTO.createUser) &&
        Objects.equals(this.updateUser, newsDTO.updateUser) &&
        Objects.equals(this.createTime, newsDTO.createTime) &&
        Objects.equals(this.updateTime, newsDTO.updateTime);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stye, title, intro, author, backImg, subject, createUser, updateUser, createTime, updateTime);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class NewsDTO {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    stye: ").append(toIndentedString(stye)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    intro: ").append(toIndentedString(intro)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    backImg: ").append(toIndentedString(backImg)).append("\n");
    sb.append("    subject: ").append(toIndentedString(subject)).append("\n");
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

