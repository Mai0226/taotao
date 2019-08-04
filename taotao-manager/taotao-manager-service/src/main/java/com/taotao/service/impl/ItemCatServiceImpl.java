package com.taotao.service.impl;


import com.taotao.commom.pojo.EasyUITreeNode;
import com.taotao.mapper.ItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;
    @Override
    public List<EasyUITreeNode> getNodeById(Long id) {
        List<TbItemCat> tbItemCats = itemCatMapper.findTbItemByParentId(id);
        List<EasyUITreeNode> easyUITreeNodes = new ArrayList<>();
        for (TbItemCat tbItemCat:tbItemCats) {
            EasyUITreeNode easyUIResultNode = new EasyUITreeNode();
            easyUIResultNode.setId(tbItemCat.getId());
            easyUIResultNode.setText(tbItemCat.getName());
            easyUIResultNode.setState(tbItemCat.getIsParent()?"closed":"open");
            easyUITreeNodes.add(easyUIResultNode);
        }
        return easyUITreeNodes;
    }
}
