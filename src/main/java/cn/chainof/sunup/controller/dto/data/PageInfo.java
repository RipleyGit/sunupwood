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
 * PageInfo
 */
@Validated

public class PageInfo   {
  @JsonProperty("pageIndex")
  private Integer pageIndex = null;

  @JsonProperty("pageSize")
  private Integer pageSize = null;

  @JsonProperty("total")
  private Integer total = null;

  public PageInfo pageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
    return this;
  }

  /**
   * 当前页数
   * @return pageIndex
  **/
  @ApiModelProperty(value = "当前页数")


  public Integer getPageIndex() {
    return pageIndex;
  }

  public void setPageIndex(Integer pageIndex) {
    this.pageIndex = pageIndex;
  }

  public PageInfo pageSize(Integer pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  /**
   * 当前页面大小
   * @return pageSize
  **/
  @ApiModelProperty(value = "当前页面大小")


  public Integer getPageSize() {
    return pageSize;
  }

  public void setPageSize(Integer pageSize) {
    this.pageSize = pageSize;
  }

  public PageInfo total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * 总数
   * @return total
  **/
  @ApiModelProperty(value = "总数")


  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PageInfo pageInfo = (PageInfo) o;
    return Objects.equals(this.pageIndex, pageInfo.pageIndex) &&
        Objects.equals(this.pageSize, pageInfo.pageSize) &&
        Objects.equals(this.total, pageInfo.total);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pageIndex, pageSize, total);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class PageInfo {\n");
    
    sb.append("    pageIndex: ").append(toIndentedString(pageIndex)).append("\n");
    sb.append("    pageSize: ").append(toIndentedString(pageSize)).append("\n");
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
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

