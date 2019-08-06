package com.taotao.service;


import com.taotao.common.pojo.EasyUITreeNode;

import java.util.List;

public interface ItemCatService {
    List<EasyUITreeNode> getNodeById(Long id);
}
