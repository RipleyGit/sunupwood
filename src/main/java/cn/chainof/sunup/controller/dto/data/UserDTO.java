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
 * UserDTO
 */
@Validated

public class UserDTO   {
  @JsonProperty("name")
  private String name = null;

  @JsonProperty("password")
  private String password = null;

  public UserDTO name(String name) {
    this.name = name;
    return this;
  }

  /**
   * 用户名
   * @return name
  **/
  @ApiModelProperty(value = "用户名")


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UserDTO password(String password) {
    this.password = password;
    return this;
  }

  /**
   * 用户密码
   * @return password
  **/
  @ApiModelProperty(value = "用户密码")


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    UserDTO userDTO = (UserDTO) o;
    return Objects.equals(this.name, userDTO.name) &&
        Objects.equals(this.password, userDTO.password);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, password);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class UserDTO {\n");
    
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
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

