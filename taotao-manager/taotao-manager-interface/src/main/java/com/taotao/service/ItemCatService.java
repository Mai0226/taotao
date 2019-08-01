package com.taotao.service;

import com.taotao.commom.pojo.EasyUIResultNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUIResultNode> getNodeById(Long id);
}
