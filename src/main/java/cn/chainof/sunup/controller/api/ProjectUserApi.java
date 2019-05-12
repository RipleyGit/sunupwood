/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package cn.chainof.sunup.controller.api;

import cn.chainof.sunup.controller.dto.data.User;
import cn.chainof.sunup.controller.dto.data.UserDTO;
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

@Api(value = "ProjectUser", description = "the ProjectUser API")
public interface ProjectUserApi {

    @ApiOperation(value = "删除用户", nickname = "deleteById", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "删除成功") })
    @RequestMapping(value = "/project/user",
        produces = { "application/json" }, 
        method = RequestMethod.DELETE)
    ResponseEntity<Void> deleteById(@ApiParam(value = "删除的ID") @Valid @RequestParam(value = "id", required = false) String id);


    @ApiOperation(value = "获取用户信息", nickname = "getUser", notes = "", response = UserDTO.class, authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "用户列表", response = UserDTO.class) })
    @RequestMapping(value = "/project/user",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<UserDTO> getUser(@ApiParam(value = "") @Valid @RequestParam(value = "id", required = false) String id);


    @ApiOperation(value = "获取用户列表", nickname = "getUserList", notes = "", response = UserDTO.class, responseContainer = "List", authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "用户列表", response = UserDTO.class, responseContainer = "List") })
    @RequestMapping(value = "/project/user/queryList",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<List<UserDTO>> getUserList(@ApiParam(value = "关键字") @Valid @RequestParam(value = "key", required = false) String key,@ApiParam(value = "当前页数", defaultValue = "0") @Valid @RequestParam(value = "pageIndex", required = false, defaultValue="0") Integer pageIndex,@ApiParam(value = "页面大小", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize);


    @ApiOperation(value = "用户登陆", nickname = "login", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success") })
    @RequestMapping(value = "/project/user/login",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User user);


    @ApiOperation(value = "用户登出", nickname = "loginOut", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success") })
    @RequestMapping(value = "/project/user/loginOut",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<Void> loginOut();


    @ApiOperation(value = "注册用户", nickname = "registerUser", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "success") })
    @RequestMapping(value = "/project/user/register",
        produces = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Void> registerUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserDTO user);


    @ApiOperation(value = "修改用户信息（用户名不允许修改）", nickname = "updateUser", notes = "", authorizations = {
        @Authorization(value = "token")
    }, tags={ "ProjectUser", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "修改成功") })
    @RequestMapping(value = "/project/user",
        produces = { "application/json" }, 
        method = RequestMethod.PUT)
    ResponseEntity<Void> updateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserDTO user);

}