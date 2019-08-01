package com.taotao.service.impl;

import com.taotao.commom.pojo.HttpUtil;
import com.taotao.commom.pojo.IDUtils;
import com.taotao.commom.pojo.PicResult;
import com.taotao.service.PicService;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
@Service
public class PicServiceImpl implements PicService {
    @Override
    public PicResult upload(byte[] bytes, String name) {
        String endpoint = "oss-cn-beijing.aliyuncs.com";
        String accessKeyId = "LTAI5SHvLRBBmvvI";
        String accessKeySecret = "WbzbzVl4ouV6EOij9nsVIWnsOcxMku";
        String bucketName = "max-taotao";
        String newName = IDUtils.genImageName() + name.substring(name.lastIndexOf("."));
        ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
        String url = HttpUtil.uploadFile(endpoint,accessKeyId,accessKeySecret,bucketName,name,bis);
        PicResult picResult = new PicResult();
        picResult.setError(0);
        picResult.setUrl(url);
        return picResult;
    }
}
