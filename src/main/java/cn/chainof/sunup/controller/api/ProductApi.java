/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package cn.chainof.sunup.controller.api;

import cn.chainof.sunup.controller.dto.data.ProductDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Api(value = "Product", description = "the Product API")
public interface ProductApi {

    @ApiOperation(value = "添加商品", nickname = "addProductInfo", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "添加成功") })
    @RequestMapping(value = "/project/product/info",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> addProductInfo(@ApiParam(value = "" ,required=true )  @Valid @RequestBody ProductDTO productInfo);


    @ApiOperation(value = "删除商品", nickname = "deleteProducts", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "删除成功") })
    @RequestMapping(value = "/project/product/info",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteProducts(@NotNull @ApiParam(value = "", required = true) @Valid @RequestParam(value = "ids", required = true) List<String> ids);


    @ApiOperation(value = "获取商品列表", nickname = "getProducts", notes = "", response = ProductDTO.class, responseContainer = "List", authorizations = {
        @Authorization(value = "token")
    }, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "商品列表", response = ProductDTO.class, responseContainer = "List") })
    @RequestMapping(value = "/project/product/info",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> getProducts(@ApiParam(value = "") @Valid @RequestParam(value = "key", required = false) String key,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false) Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize);


    @ApiOperation(value = "修改商品信息", nickname = "modifyProductInfo", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "修改完成") })
    @RequestMapping(value = "/project/product/info",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> modifyProductInfo(@ApiParam(value = "id,name不能为空" ,required=true )  @Valid @RequestBody ProductDTO productInfo);


    @ApiOperation(value = "获取分类下的所有商品", nickname = "queryListByItem", notes = "", response = ProductDTO.class, responseContainer = "List", authorizations = {
        @Authorization(value = "token")
    }, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "商品列表", response = ProductDTO.class, responseContainer = "List") })
    @RequestMapping(value = "/project/product/listbyItem",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> queryListByItem(@NotNull @ApiParam(value = "必填 分类ID", required = true) @Valid @RequestParam(value = "itemId", required = true) String itemId,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false) Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize);


    @ApiOperation(value = "获取标签下的所有商品", nickname = "queryListByLabel", notes = "", response = ProductDTO.class, responseContainer = "List", authorizations = {
        @Authorization(value = "token")
    }, tags={ "Product", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "商品列表", response = ProductDTO.class, responseContainer = "List") })
    @RequestMapping(value = "/project/product/listbyLabel",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<ProductDTO>> queryListByLabel(@NotNull @ApiParam(value = "必填 标签ID", required = true) @Valid @RequestParam(value = "labelId", required = true) String labelId,@ApiParam(value = "当前页数") @Valid @RequestParam(value = "pageIndex", required = false) Integer pageIndex,@ApiParam(value = "页面大小") @Valid @RequestParam(value = "pageSize", required = false) Integer pageSize);

}
