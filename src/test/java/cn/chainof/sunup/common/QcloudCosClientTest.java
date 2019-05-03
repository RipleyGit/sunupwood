package cn.chainof.sunup.common;

import cn.chainof.sunup.SunUpApplication;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = SunUpApplication.class)
@Slf4j
public class QcloudCosClientTest {

    @Autowired
    private QcloudCosClient qcloudCosClient;

    @Test
    public void test() {
        COSClient cosClient = qcloudCosClient.getCosClient();
        File localFile = new File("/Users/yubs/project/personal/sunupwood/src/main/resources/static/swagger/favicon-16x16.png");
        // 指定要上传到 COS 上对象键
        String key = "img/upload_single_demo.png";

        PutObjectRequest putObjectRequest = new PutObjectRequest(qcloudCosClient.getBucketName(), key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        qcloudCosClient.cosShutdown();
    }

}