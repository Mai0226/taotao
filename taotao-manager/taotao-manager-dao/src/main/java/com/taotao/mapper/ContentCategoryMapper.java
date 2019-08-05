package com.taotao.mapper;

import com.taotao.pojo.TbContentCategory;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ContentCategoryMapper {
    @Select("SELECT * from tbcontentcategory where parentId = #{parentId}")
    List<TbContentCategory> getContentCategorysByParentId(Long parentId);
    @Select("SELECT * FROM tbcontentcategory WHERE id = #{parentId}")
    TbContentCategory findContentCategoryById(Long parentId);
    @Update("update tbcontentcategory set isParent = #{isParent},name = #{name} where id =#{id}")
    void updateContentCategory(TbContentCategory tbContentCategory1);
    @Insert("insert into tbcontentcategory ( parentId, name, status, sortOrder, isParent, created, updated) value (#{parentId},#{name},#{status},#{sortOrder},#{isParent},#{created},#{updated})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id" )
    void addContentCategory(TbContentCategory tbContentCategory);
    @Delete("delete from tbcontentcategory where id = #{id}")
    int deleteCategoryById(Long id);
    @Select("select parentId from tbcontentcategory where id = #{id}")
    Long getParentId(TbContentCategory tbContentCategory);
}
