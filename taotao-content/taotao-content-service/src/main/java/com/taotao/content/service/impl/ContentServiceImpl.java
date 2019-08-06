package com.taotao.content.service.impl;

import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.JsonUtils;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.content.jedis.JedisClient;
import com.taotao.content.service.ContentService;
import com.taotao.mapper.ContentMapper;
import com.taotao.pojo.TbContent;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentMapper contentMapper;
    @Autowired
    private JedisClient jedisClient;
    @Override
    public EasyUIResult getContentByCategoryId(Long categoryId) {
        List<TbContent> tbContents = contentMapper.getContentsByCategoryId(categoryId);
        EasyUIResult easyUIResult = new EasyUIResult();
        easyUIResult.setRows(tbContents);
        easyUIResult.setTotal((long) tbContents.size());
        return easyUIResult;
    }
    /*添加广告内容的方法，加入缓存*/
    @Override
    public TaotaoResult addContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setCreated(date);
        tbContent.setUpdated(date);
        contentMapper.addContent(tbContent);
        jedisClient.del("AD1");
        return TaotaoResult.ok(tbContent);
    }
    /*删除广告内容的方法，加入缓存*/
    @Override
    public TaotaoResult deleteContents(Long[] ids) {
        contentMapper.deleteContentByIds(ids);
        jedisClient.del("AD1");
        return TaotaoResult.ok();
    }
    /*根据id获取广告内容，这里加入缓存*/
    @Override
    public List<TbContent> findContentByCategoryId(Long ad1_cid) {
        String json = jedisClient.get("AD1");
        if (StringUtils.isNotBlank(json)){
            List<TbContent> tbContents = JsonUtils.jsonToList(json,TbContent.class);
            System.out.println("从缓存中获取数据");
            return tbContents;
        }
        List<TbContent> tbContents = contentMapper.findContentByCId(ad1_cid);
        jedisClient.set("AD1", JsonUtils.objectToJson(tbContents));
        System.out.println("从数据库中获取");
        return tbContents;
    }
    /*更新广告内容的方法，加入缓存*/
    @Override
    public TaotaoResult updateContent(TbContent tbContent) {
        Date date = new Date();
        tbContent.setUpdated(date);
        contentMapper.updateContent(tbContent);
        jedisClient.del("AD1");
        return TaotaoResult.ok();
    }
}
