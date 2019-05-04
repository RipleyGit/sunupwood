package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.EmailApi;
import cn.chainof.sunup.controller.dto.data.EmailMsgDTO;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
public class EmailApiController implements EmailApi {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;
    @Value("${spring.mail.to}")
    private String to;

    @Override
    public ResponseEntity<Void> sendEmailMsg(@ApiParam(value = ""  )  @Valid @RequestBody EmailMsgDTO emailMsgDTO){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(emailMsgDTO.getTitle());
        message.setText(emailMsgDTO.getConcent()+"\n联系方式:"+emailMsgDTO.getEmail());
        mailSender.send(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}
