package com.taotao.service;

import com.taotao.common.pojo.PicResult;

public interface PicService {
    PicResult upload(byte[] bytes,String name);
}
