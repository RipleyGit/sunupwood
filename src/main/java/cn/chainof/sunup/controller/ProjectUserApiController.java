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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;


@RestController
@Slf4j
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
    public ResponseEntity<Void> updateUser(@Valid UserDTO user) {
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
    public ResponseEntity<Void> deleteById(@Valid String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserDTO> getUser(@Valid String id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUserList(@Valid String key, @Valid Integer pageIndex, @Valid Integer pageSize) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> login(@Valid User user) {
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
