package com.taotao.service;

import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.QueryVo;
import com.taotao.commom.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface ItemService {
    TbItem findiItemById(Long id);

    EasyUIResult findPageItems(int page,int rows);

    TaotaoResult deleteItems(Long[] ids);

    void deleteItemById(Long id);

    void reshelfItem(QueryVo vo);

    void instocById(Long id);

    void reshelfById(Long id);
}
