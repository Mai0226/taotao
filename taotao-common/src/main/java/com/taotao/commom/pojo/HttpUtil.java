package com.taotao.commom.pojo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;

public class HttpUtil {
    public static String uploadFile(String endpoint, String accessKeyId, String accessKeySecret, String bucketName, String fileName, ByteArrayInputStream bis){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        ossClient.putObject(bucketName, fileName, bis);
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(bucketName, fileName, expiration).toString();
        ossClient.shutdown();
        return url;
    }
}
