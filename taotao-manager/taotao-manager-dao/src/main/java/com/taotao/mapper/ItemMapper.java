package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Select;

public interface ItemMapper {
    @Select("select * from tbitem where id = #{id}")
    TbItem findItemById(Long id);
}