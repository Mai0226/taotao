package com.taotao.commom.pojo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

import java.io.*;
import java.util.Date;

public class MyTest {
    public static void main(String[] args) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。。
        String accessKeyId = "LTAI5SHvLRBBmvvI";
        String accessKeySecret = "WbzbzVl4ouV6EOij9nsVIWnsOcxMku";
        String bucketName = "max-taotao";
        String objectName="mai.jpg";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        /*InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("E:\\麦志伟\\盾生日\\DSC_0003.JPG"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        ossClient.putObject(bucketName, objectName, inputStream);*/
        byte[] content = "Hello OSS".getBytes();
        ossClient.putObject(bucketName, objectName, new ByteArrayInputStream(content));
        // 关闭OSSClient。
        ossClient.shutdown();
        Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
        String url = ossClient.generatePresignedUrl(bucketName, objectName, expiration).toString();
        System.out.println(url);
    }
}
