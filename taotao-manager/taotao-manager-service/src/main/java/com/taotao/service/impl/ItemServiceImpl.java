package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.IDUtils;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private ItemMapper itemMapper;

    @Override
    public TbItem findiItemById(Long id) {
        return itemMapper.findItemById(id);
    }

    @Override
    public EasyUIResult findPageItems(int page, int rows) {
        //分页显示初始化
        PageHelper.startPage(page,rows);
        //查询所有的商品信息
        List<TbItem> items = itemMapper.findAllItems();
        PageInfo<TbItem> pageInfo = new PageInfo<>(items);
        EasyUIResult easyUIResult = new EasyUIResult();
        //使用插件获取总条数
        easyUIResult.setTotal(pageInfo.getTotal());
        easyUIResult.setRows(items);
        return easyUIResult;
    }

    @Override
    public TaotaoResult deleteItems(Long[] ids) {
       int i = itemMapper.deleteItems(ids);
        if (i>=0){
            return TaotaoResult.ok();
        }
        return null;
    }


    @Override
    public TaotaoResult instocByIds(Long[] ids) {
        int i = itemMapper.instocByIds(ids);
        if (i>=0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult reshelfByIds(Long[] ids) {
        int i = itemMapper.reshelfByIds(ids);
        if (i>=0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult addItems(TbItem tbItem, String desc) {
        Long itemId = IDUtils.genItemId();
        Date date = new Date();
        tbItem.setId(itemId);
        tbItem.setCreated(date);
        tbItem.setUpdated(date);
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        int itemCount= itemMapper.addItem(tbItem);
        int itemDescCount = itemMapper.addItemDesc(tbItemDesc);
        if (itemCount!=0&&itemDescCount!=0){
            return TaotaoResult.ok();
        }
        return null;
    }

    @Override
    public TaotaoResult getItemDesc(Long id) {
        TbItemDesc tbItemDesc = itemMapper.getItemDesc(id);
        return TaotaoResult.ok(tbItemDesc);
    }

    @Override
    public TaotaoResult updateItem(TbItem tbItem, String desc) {
        Date date = new Date();
        Long itemId  =tbItem.getId();
        int descCount = itemMapper.updateDesc(desc,itemId);
        tbItem.setUpdated(date);
        int itemCount = itemMapper.updateItem(tbItem);
        if(descCount!=0&&itemCount!=0){
            return TaotaoResult.ok();
        }
        return null;
    }
}
