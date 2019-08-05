package com.taotao.controller;

import com.taotao.commom.pojo.EasyUITreeNode;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EasyUITreeNode> findAllContentCategory(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EasyUITreeNode> easyUITreeNodes = contentCategoryService.findContentCategoryByParentId(parentId);
        return easyUITreeNodes;
    }
    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult creatCate(Long parentId,String name){
        TaotaoResult taotaoResult = contentCategoryService.addCate(parentId,name);
        return taotaoResult;
    }
    @RequestMapping("/delete")
    @ResponseBody
    public void deleteCategoryId(Long parentId,Long id){
        contentCategoryService.deleteCategoryById(id);
    }
    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult updateCategory(Long id,String name){
        TaotaoResult taotaoResult =contentCategoryService.updateCategory(id,name);
        return taotaoResult;
    }
}
