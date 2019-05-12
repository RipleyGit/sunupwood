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
 * EmailMsgDTO
 */
@Validated

public class EmailMsgDTO   {
  @JsonProperty("title")
  private String title = null;

  @JsonProperty("concent")
  private String concent = null;

  @JsonProperty("email")
  private String email = null;

  @JsonProperty("phone")
  private String phone = null;

  @JsonProperty("code")
  private String code = null;

  public EmailMsgDTO title(String title) {
    this.title = title;
    return this;
  }

  /**
   * 标题
   * @return title
  **/
  @ApiModelProperty(value = "标题")


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public EmailMsgDTO concent(String concent) {
    this.concent = concent;
    return this;
  }

  /**
   * 内容
   * @return concent
  **/
  @ApiModelProperty(value = "内容")


  public String getConcent() {
    return concent;
  }

  public void setConcent(String concent) {
    this.concent = concent;
  }

  public EmailMsgDTO email(String email) {
    this.email = email;
    return this;
  }

  /**
   * 联系方式
   * @return email
  **/
  @ApiModelProperty(value = "联系方式")


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public EmailMsgDTO phone(String phone) {
    this.phone = phone;
    return this;
  }

  /**
   * 手机号码
   * @return phone
  **/
  @ApiModelProperty(value = "手机号码")


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public EmailMsgDTO code(String code) {
    this.code = code;
    return this;
  }

  /**
   * 代码
   * @return code
  **/
  @ApiModelProperty(value = "代码")


  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EmailMsgDTO emailMsgDTO = (EmailMsgDTO) o;
    return Objects.equals(this.title, emailMsgDTO.title) &&
        Objects.equals(this.concent, emailMsgDTO.concent) &&
        Objects.equals(this.email, emailMsgDTO.email) &&
        Objects.equals(this.phone, emailMsgDTO.phone) &&
        Objects.equals(this.code, emailMsgDTO.code);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, concent, email, phone, code);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EmailMsgDTO {\n");
    
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    concent: ").append(toIndentedString(concent)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    phone: ").append(toIndentedString(phone)).append("\n");
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
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

