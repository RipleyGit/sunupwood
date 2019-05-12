package cn.chainof.sunup.controller;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.controller.api.ProjectUserApi;
import cn.chainof.sunup.controller.dto.data.User;
import cn.chainof.sunup.controller.dto.data.UserDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.service.ProjectUserService;
import cn.chainof.sunup.utils.AutoConvertUtil;
import com.github.pagehelper.util.StringUtil;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@Slf4j
@CrossOrigin
@RestController
public class ProjectUserApiController implements ProjectUserApi {

    @Autowired
    private ProjectUserService projectUserService;

    @Override
    public ResponseEntity<Void> registerUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserDTO user){
        if (StringUtil.isEmpty(user.getName())|| StringUtil.isEmpty(user.getPassword())){
            throw new ClientException("用户名和密码不能为空");
        }
        if (user.getName().length()>10){
            throw new ClientException("用户名长度不能超过10个字符");
        }
        if (user.getPassword().length()>16){
            throw new ClientException("密码不能超过16个字符");
        }
        ProjectUser projectUser = AutoConvertUtil.autoConvertTo(user, ProjectUser.class);
        projectUserService.addNewUser(projectUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserDTO user) {
        if (user.getName().length()>10){
            throw new ClientException("用户名长度不能超过10个字符");
        }
        if (user.getPassword().length()>16){
            throw new ClientException("密码不能超过16个字符");
        }
        ProjectUser projectUser = AutoConvertUtil.autoConvertTo(user, ProjectUser.class);
        projectUserService.updateUser(projectUser);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Void> deleteById(@ApiParam(value = "删除的ID") @Valid @RequestParam(value = "id", required = false) String id){
        projectUserService.deletedById(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUser(@ApiParam(value = "") @Valid @RequestParam(value = "id", required = false) String id){
        ProjectUser user = projectUserService.getUserById(id);
        UserDTO dto = AutoConvertUtil.autoConvertTo(user, UserDTO.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dto,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUserList(@ApiParam(value = "关键字") @Valid @RequestParam(value = "key", required = false) String key,@ApiParam(value = "当前页数", defaultValue = "0") @Valid @RequestParam(value = "pageIndex", required = false, defaultValue="0") Integer pageIndex,@ApiParam(value = "页面大小", defaultValue = "10") @Valid @RequestParam(value = "pageSize", required = false, defaultValue="10") Integer pageSize){
        List<ProjectUser> userList = projectUserService.queryList(key,pageIndex,pageSize);
        List<UserDTO> dtoList = AutoConvertUtil.convert2List(userList, UserDTO.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(dtoList,headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody User user){
        if (StringUtil.isEmpty(user.getName())|| StringUtil.isEmpty(user.getPassword())){
            throw new ClientException("用户名和密码不能为空");
        }
        ProjectUser projectUser = projectUserService.getByName(user.getName());
        if (user.getPassword().equals(projectUser.getPassword())){
            UserContext.setUserSession(projectUser);
        }else {
            throw new ClientException("用户名或密码错误");
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> loginOut() {

        UserContext.removeUserSession();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

}
