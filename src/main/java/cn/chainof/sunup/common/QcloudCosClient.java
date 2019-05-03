package cn.chainof.sunup.common;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.region.Region;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class QcloudCosClient {

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

    private COSClient cosClient;

    public COSClient getCosClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        COSCredentials cred = new BasicCOSCredentials(COS_SECRETID, COS_SECRETKEY);
        // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        ClientConfig clientConfig = new ClientConfig(new Region(COS_REGION));
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }


    public void cosShutdown(){
        cosClient.shutdown();
    }

    public void sout(){
        System.out.println(COS_APPID+COS_SECRETID+COS_SECRETKEY+COS_BUCKETNAME+COS_REGION+COS_IMGURL);
    }

    public String getBucketName(){
        return COS_BUCKETNAME;
    }



}
