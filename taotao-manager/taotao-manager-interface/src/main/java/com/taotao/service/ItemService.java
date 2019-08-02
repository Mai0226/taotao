package com.taotao.service;

import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.QueryVo;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;

import java.util.List;

public interface ItemService {
    TbItem findiItemById(Long id);

    EasyUIResult findPageItems(int page,int rows);

    TaotaoResult deleteItems(Long[] ids);


    TaotaoResult instocByIds(Long[] ids);

    TaotaoResult reshelfByIds(Long[] ids);

    TaotaoResult addItems(TbItem tbItem, String desc);
}
