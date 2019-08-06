package com.taotao.content.service.impl;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.content.service.ContentCategoryService;
import com.taotao.mapper.ContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
    @Autowired
    private ContentCategoryMapper contentCategoryMapper;
    @Override
    public List<EasyUITreeNode> findContentCategoryByParentId(Long parentId) {
        List<TbContentCategory> tbContentCategories = contentCategoryMapper.getContentCategorysByParentId(parentId);
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbContentCategory contentCategory: tbContentCategories) {
            EasyUITreeNode easyUITreeNode = new EasyUITreeNode();
            easyUITreeNode.setId(contentCategory.getId());
            easyUITreeNode.setText(contentCategory.getName());
            easyUITreeNode.setState(contentCategory.getIsParent()?"closed":"open");
            easyUITreeNodes.add(easyUITreeNode);
        }
        return easyUITreeNodes;
    }

    @Override
    public TaotaoResult addCate(Long parentId, String name) {
        Date date = new Date();
        TbContentCategory tbContentCategory = new TbContentCategory();
        tbContentCategory.setName(name);
        tbContentCategory.setCreated(date);
        tbContentCategory.setUpdated(date);
        tbContentCategory.setIsParent(false);
        tbContentCategory.setStatus(1);
        tbContentCategory.setSortOrder(1);
        tbContentCategory.setParentId(parentId);
        contentCategoryMapper.addContentCategory(tbContentCategory);
        TbContentCategory tbContentCategory1 = contentCategoryMapper.findContentCategoryById(parentId);
        if (!tbContentCategory1.getIsParent()){
            tbContentCategory1.setIsParent(true);
            contentCategoryMapper.updateContentCategory(tbContentCategory1);
        }
        return TaotaoResult.ok(tbContentCategory);
    }
    /*递归删除*/
    @Override
    public void deleteCategoryById(Long id) {
        /*TbContentCategory tbContentCategory = contentCategoryMapper.findContentCategoryById(id);
        if (tbContentCategory.getIsParent()){
            List<TbContentCategory> tbContentCategories = contentCategoryMapper.getContentCategorysByParentId(id);
            for (TbContentCategory tbcontent:tbContentCategories) {
                deleteCategoryById(tbcontent.getId());
            }
            tbContentCategory.setIsParent(false);
            contentCategoryMapper.updateContentCategory(tbContentCategory);
            contentCategoryMapper.deleteCategoryById(id);
        }else {
            contentCategoryMapper.deleteCategoryById(id);
        }*/
        List<TbContentCategory> tbContentCategories = contentCategoryMapper.getContentCategorysByParentId(id);
        if (tbContentCategories.size()!=0){
            for (TbContentCategory tbcontent:tbContentCategories) {
                deleteCategoryById(tbcontent.getId());
            }
        }
        contentCategoryMapper.deleteCategoryById(id);
    }

    @Override
    public TaotaoResult updateCategory(Long id, String name) {
        TbContentCategory tbContentCategory = contentCategoryMapper.findContentCategoryById(id);
        tbContentCategory.setName(name);
        contentCategoryMapper.updateContentCategory(tbContentCategory);
        return TaotaoResult.ok();
    }
}
