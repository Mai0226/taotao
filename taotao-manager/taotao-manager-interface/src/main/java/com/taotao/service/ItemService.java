package com.taotao.service;

import com.taotao.common.pojo.EasyUIResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem findiItemById(Long id);

    EasyUIResult findPageItems(int page,int rows);

    TaotaoResult deleteItems(Long[] ids);

    TaotaoResult instocByIds(Long[] ids);

    TaotaoResult reshelfByIds(Long[] ids);

    TaotaoResult addItems(TbItem tbItem, String desc);

    TaotaoResult getItemDesc(Long id);

    TaotaoResult updateItem(TbItem tbItem, String desc);
}
