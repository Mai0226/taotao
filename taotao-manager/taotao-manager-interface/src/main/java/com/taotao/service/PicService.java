package com.taotao.service;

import com.taotao.commom.pojo.PicResult;

public interface PicService {
    PicResult upload(byte[] bytes,String name);
}
