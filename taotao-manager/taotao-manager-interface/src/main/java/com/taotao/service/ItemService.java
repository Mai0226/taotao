package com.taotao.service;

import com.taotao.commom.pojo.EasyUIResult;
import com.taotao.commom.pojo.QueryVo;
import com.taotao.pojo.TbItem;

import java.util.List;

public interface ItemService {
    TbItem findiItemById(Long id);

    EasyUIResult findPageItems(int page,int rows);

    void deleteItems(QueryVo vo);

    void deleteItemById(Long id);

    void reshelfItem(QueryVo vo);

    void instocById(Long id);

    void reshelfById(Long id);
}
