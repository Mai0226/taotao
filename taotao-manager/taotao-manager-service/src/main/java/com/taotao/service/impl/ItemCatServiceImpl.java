package com.taotao.service.impl;

import com.taotao.commom.pojo.EasyUIResultNode;
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
    public List<EasyUIResultNode> getNodeById(Long id) {
        List<TbItemCat> tbItemCats = itemCatMapper.findTbItemByParentId(id);
        List<EasyUIResultNode> easyUIResultNodes = new ArrayList<>();
        for (TbItemCat tbItemCat:tbItemCats) {
            EasyUIResultNode easyUIResultNode = new EasyUIResultNode();
            easyUIResultNode.setId(tbItemCat.getId());
            easyUIResultNode.setText(tbItemCat.getName());
            easyUIResultNode.setState(tbItemCat.getIsParent()?"closed":"open");
            easyUIResultNodes.add(easyUIResultNode);
        }
        return easyUIResultNodes;
    }
}
