package com.taotao.mapper;

import com.taotao.commom.pojo.QueryVo;
import com.taotao.pojo.TbItem;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ItemMapper {
    @Select("select * from tbitem where id = #{id}")
    TbItem findItemById(Long id);

    @Select("select * from tbitem")
    List<TbItem> findAllItems();

    @Delete("<script>delete from tbitem where id in <foreach collection='array' open='(' close=')' separator= ',' item='id'>#{id}</foreach></script>")
    int deleteItems(Long[] ids);

    @Update("<script>update tbitem set status = 2 where id in <foreach collection='array' open='(' close=')' separator= ',' item='id'>#{id}</foreach></script>")
    int instocByIds(Long[] ids);
    @Update("<script>update tbitem set status = 1 where id in <foreach collection='array' open='(' close=')' separator= ',' item='id'>#{id}</foreach></script>")
    int reshelfByIds(Long[] ids);
}
