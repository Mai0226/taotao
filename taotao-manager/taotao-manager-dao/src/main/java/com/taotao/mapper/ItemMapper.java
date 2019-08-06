package com.taotao.mapper;

import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemDesc;
import org.apache.ibatis.annotations.*;

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
    @Insert("insert  into tbitem(id, title, sellPoint, price, num, barcode, image, cid, created, updated) VALUE (#{id},#{title},#{sellPoint},#{price},#{num},#{barcode},#{image},#{cid},#{created},#{updated})")
    int addItem(TbItem tbItem);
    @Insert("insert into tbitemdesc (itemId, itemDesc, created, updated) VALUE (#{itemId},#{itemDesc},#{created},#{updated})")
    int addItemDesc(TbItemDesc tbItemDesc);
    @Select("SELECT * from tbitemdesc where itemId = #{id}")
    TbItemDesc getItemDesc(Long id);
    @Update("update tbitemdesc set itemDesc = #{desc} where itemId = #{itemId}")
    int updateDesc(@Param("desc") String desc,@Param("itemId") Long itemId);
    @Update("update tbitem set title = #{title},sellPoint=#{sellPoint},price =#{price},num=#{num},barcode=#{barcode},image=#{image},cid=#{cid},updated=#{updated} where id = #{id}")
    int updateItem(TbItem tbItem);
}
