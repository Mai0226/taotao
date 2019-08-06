package com.taotao.common.pojo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.Callback;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;

import java.io.*;
import java.util.Date;

public class MyTest {
    public static void test2(String[] args) throws Throwable {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。。
        String accessKeyId = "LTAI5SHvLRBBmvvI";
        String accessKeySecret = "WbzbzVl4ouV6EOij9nsVIWnsOcxMku";
        String bucketName = "max-taotao";
        String objectName = "hahaha";

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        ObjectMetadata meta = new ObjectMetadata();
        Callback callback = new Callback();
        meta.setContentType("image/jpg");
        UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, objectName);

        // 通过UploadFileRequest设置单个参数。
        // 设置存储空间名称。
        //uploadFileRequest.setBucketName("<yourBucketName>");
        // 设置文件名称。
        //uploadFileRequest.setKey("<yourObjectName>");
        // 指定上传的本地文件。
        uploadFileRequest.setUploadFile(String.valueOf(new File("E:\\麦志伟\\春游\\DSC_0860.JPG")));
        // 指定上传并发线程数，默认为1。
        uploadFileRequest.setTaskNum(5);
        // 指定上传的分片大小，范围为100KB~5GB，默认为文件大小/10000。
        uploadFileRequest.setPartSize(1 * 1024 * 1024);
        // 开启断点续传，默认关闭。
        uploadFileRequest.setEnableCheckpoint(true);
        // 记录本地分片上传结果的文件。开启断点续传功能时需要设置此参数，上传过程中的进度信息会保存在该文件中，如果某一分片上传失败，再次上传时会根据文件中记录的点继续上传。上传完成后，该文件会被删除。默认与待上传的本地文件同目录，为uploadFile.ucp。
        uploadFileRequest.setCheckpointFile("checkpoint.txt");
        // 文件的元数据。
        uploadFileRequest.setObjectMetadata(meta);
        // 设置上传成功回调，参数为Callback类型。
        uploadFileRequest.setCallback(callback);

        // 断点续传上传。
        ossClient.uploadFile(uploadFileRequest);

        // 关闭OSSClient。
        ossClient.shutdown();
    }


    public static void test1() {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。。
        String accessKeyId = "LTAI5SHvLRBBmvvI";
        String accessKeySecret = "WbzbzVl4ouV6EOij9nsVIWnsOcxMku";
        String bucketName = "max-taotao";
        String objectName = "mai.jpg";
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
