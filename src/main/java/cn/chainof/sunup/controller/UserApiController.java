package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.UserApi;
import cn.chainof.sunup.controller.dto.data.UserRegisterDTO;
import cn.chainof.sunup.exception.ClientException;
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
    public ResponseEntity<Void> signinUser(@ApiParam(value = "" ,required=true )  @Valid @RequestBody UserRegisterDTO userRegisterDTO) {
        if (StringUtil.isEmpty(userRegisterDTO.getName())|| StringUtil.isEmpty(userRegisterDTO.getPassword())){
            throw new ClientException("用户名和密码不能为空");
        }
        userService.signinUser(userRegisterDTO.getName(),userRegisterDTO.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
