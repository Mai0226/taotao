package com.taotao.controller;

import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.content.service.ContentService;
import com.taotao.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ContentController {
    @Autowired
    private ContentService contentService;
    @RequestMapping("/content/query/list")
    @ResponseBody
    public EasyUIResult findAllContentByCategoryId(Long categoryId){
        EasyUIResult easyUIResult = contentService.getContentByCategoryId(categoryId);
        return easyUIResult;
    }
    @RequestMapping("/content/save")
    @ResponseBody
    public TaotaoResult addContent(TbContent tbContent){
        TaotaoResult taotaoResult = contentService.addContent(tbContent);
        return taotaoResult;
    }
    @RequestMapping("/content/delete")
    @ResponseBody
    public TaotaoResult deleteContent(Long[] ids){
        TaotaoResult taotaoResult = contentService.deleteContents(ids);
        return taotaoResult;
    }
    @RequestMapping("/rest/content/edit")
    @ResponseBody
    public TaotaoResult updateContent(TbContent tbContent){
        System.out.println(tbContent);
        TaotaoResult taotaoResult = contentService.updateContent(tbContent);
        return taotaoResult;
    }
}
