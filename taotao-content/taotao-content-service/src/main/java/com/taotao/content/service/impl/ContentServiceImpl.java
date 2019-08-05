package com.taotao.content.service.impl;

import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Override
    public EasyUIResult getContentByCategoryId(Long categoryId) {
        List<TbContent> tbContents = contentMapper.getContentsByCategoryId(categoryId);
        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setRows(tbContents);
        easyUIResult.setTotal((long) tbContents.size());
        return easyUIResult;
    }

    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        contentMapper.addContent(tbContent);
        return TaotaoResult.ok(tbContent);
    }

    @Override
    public TaotaoResult deleteContents(Long[] ids) {
        contentMapper.deleteContentByIds(ids);
        return TaotaoResult.ok();
    }

    @Override
    public List<TbContent> findContentByCategoryId(Long ad1_cid) {
        List<TbContent> tbContents = contentMapper.findContentByCId(ad1_cid);
        return tbContents;
    }

    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        contentMapper.updateContent(tbContent);
        return TaotaoResult.ok();
    }
}
