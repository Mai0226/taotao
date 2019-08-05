package com.taotao.mapper;

import com.taotao.pojo.TbContent;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ContentMapper {
    @Select("SELECT * from tbcontent where categoryId = #{categoryId}")
    List<TbContent> getContentsByCategoryId(Long categoryId);
    @Insert("insert into tbcontent (categoryId, title, subTitle, titleDesc, url, pic, pic2, content, created, updated)value (#{categoryId},#{title},#{subTitle},#{titleDesc},#{url},#{pic},#{pic2},#{content},#{created},#{updated})")
    void addContent(TbContent tbContent);
    @Delete("<script>delete from tbcontent where id in <foreach collection='array' open='(' close=')' separator= ',' item='id'>#{id}</foreach></script>")
    void deleteContentByIds(Long[] ids);
    @Select("SELECT * from tbcontent where categoryId = #{ad1_cid}")
    List<TbContent> findContentByCId(Long ad1_cid);
    @Update("update tbcontent set title=#{title},subTitle=#{subTitle},titleDesc=#{titleDesc},url=#{url},pic=#{pic},pic2=#{pic2},content = #{content},updated=#{updated} where id = #{id}")
    void updateContent(TbContent tbContent);
}
