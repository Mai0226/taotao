package com.taotao.content.service;

import com.taotao.common.pojo.EasyUITreeNode;
import com.taotao.common.pojo.TaotaoResult;

import java.util.List;
public interface ContentCategoryService {
    List<EasyUITreeNode> findContentCategoryByParentId(Long parentId);

    TaotaoResult addCate(Long parentId, String name);

    void deleteCategoryById(Long id);

    TaotaoResult updateCategory(Long id, String name);
}
