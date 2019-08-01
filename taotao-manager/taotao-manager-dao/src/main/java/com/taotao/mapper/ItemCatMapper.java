package com.taotao.mapper;

import com.taotao.pojo.TbItemCat;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemCatMapper {

    @Select("select * from tbitemcat where parentId  =#{id}")
    List<TbItemCat> findTbItemByParentId(Long id);
}
