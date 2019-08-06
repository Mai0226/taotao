package com.taotao.content.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {
    EasyUIResult getContentByCategoryId(Long categoryId);


    TaotaoResult addContent(TbContent tbContent);

    TaotaoResult deleteContents(Long[] ids);

    List<TbContent> findContentByCategoryId(Long ad1_cid);

    TaotaoResult updateContent(TbContent tbContent);
}
