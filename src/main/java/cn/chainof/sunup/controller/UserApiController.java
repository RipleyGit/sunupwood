package cn.chainof.sunup.controller;

import cn.chainof.sunup.common.UserContext;
import cn.chainof.sunup.controller.api.UserApi;
import cn.chainof.sunup.controller.dto.data.UserDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.service.UserService;
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


@RestController
@Slf4j
public class UserApiController implements UserApi {

    @Autowired
    private UserService userService;

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
        ProjectUser projectUser = userService.getByName(user.getName());
        if (projectUser != null){
            throw new ClientException("用户名已存在！");
        }

        userService.addNewUser(user.getName(),user.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> login(@Valid UserDTO user) {
        if (StringUtil.isEmpty(user.getName())|| StringUtil.isEmpty(user.getPassword())){
            throw new ClientException("用户名和密码不能为空");
        }
        ProjectUser projectUser = userService.getByName(user.getName());
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
