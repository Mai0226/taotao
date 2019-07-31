package com.taotao.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.QueryVo;
import com.taotao.mapper.ItemMapper;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void deleteItems(QueryVo vo) {
        itemMapper.deleteItems(vo);
    }

    @Override
    public void deleteItemById(Long id) {
        itemMapper.deleteItemById(id);
    }

    @Override
    public void reshelfItem(QueryVo vo) {
        itemMapper.reshelfItem(vo);
    }

    @Override
    public void instocById(Long id) {
        itemMapper.instocById(id);
    }

    @Override
    public void reshelfById(Long id) {
        itemMapper.reshelfById(id);
    }


}
