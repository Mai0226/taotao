package com.taotao.search.service;

import com.taotao.common.pojo.SearchResult;
import com.taotao.common.pojo.TaotaoResult;

public interface SearchItemService {
    TaotaoResult getAllItems();
    SearchResult search(String queryString,int page,int rows);

    void addDocument(Long itemId);

    void deleteDocument(Long itemId);
}
