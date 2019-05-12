package cn.chainof.sunup.controller;

import cn.chainof.sunup.controller.api.ProjectOtherApi;
import cn.chainof.sunup.controller.dto.data.EmailMsgDTO;
import cn.chainof.sunup.exception.ClientException;
import cn.chainof.sunup.model.ProjectUser;
import cn.chainof.sunup.service.ProjectUserService;
import cn.chainof.sunup.utils.KeyUtil;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.region.Region;
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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin
public class ProjectOtherApiController implements ProjectOtherApi {

    @Autowired
    private JavaMailSender mailSender;
    @Value("${spring.mail.from}")
    private String from;
    @Value("${spring.mail.to}")
    private String to;

    @Value("${qcloud.cos.AppId}")
    private String COS_APPID;
    @Value("${qcloud.cos.SecretId}")
    private String COS_SECRETID;
    @Value("${qcloud.cos.SecretKey}")
    private String COS_SECRETKEY;
    @Value("${qcloud.cos.BucketName}")
    private String COS_BUCKETNAME;
    @Value("${qcloud.cos.Region}")
    private String COS_REGION;
    @Value("${qcloud.cos.ImgUrl}")
    private String COS_IMGURL;
    private static final String PATH = "img/";

    @Autowired
    private ProjectUserService projectUserService;

    @Override
    public ResponseEntity<Void> sendEmailMsg(@ApiParam(value = ""  )  @Valid @RequestBody EmailMsgDTO emailMsgDTO){

        List<ProjectUser> userList = projectUserService.queryList(0,10);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(emailMsgDTO.getTitle());
        message.setText(emailMsgDTO.getConcent()+"\nEmail:"+emailMsgDTO.getEmail()+"\n手机号:"+emailMsgDTO.getPhone());
        mailSender.send(message);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(headers, HttpStatus.OK);
    }



    @Override
    public ResponseEntity<String> uploadImg(@Valid MultipartFile file) {
        if (file.isEmpty()) {
            throw new ClientException("上传文件为空，请重新选择");
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String key = PATH + KeyUtil.genUniqueKey();
        // key为文件名，防止生成的临时文件重复
        final File tempFile;
        // MultipartFile to File
        try {
            tempFile = File.createTempFile(key, prefix);
            file.transferTo(tempFile);
        } catch (IOException e) {
            throw new ClientException("文件异常");
        }

        key = key +prefix;

        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(COS_SECRETID, COS_SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        ClientConfig clientConfig = new ClientConfig(new Region(COS_REGION));
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(COS_BUCKETNAME, key, tempFile);
            cosClient.putObject(putObjectRequest);
        } catch (CosClientException e) {
            log.error(e.getLocalizedMessage());
            throw new ClientException("图片上传出错");
        } finally {
            cosClient.shutdown();
        }
        String imgUrl = COS_IMGURL+"/"+key;
        //程序结束时，删除临时文件
        deleteFile(tempFile);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        return new ResponseEntity<>(imgUrl, headers, HttpStatus.OK);
    }


    /**
     * 删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }
}
