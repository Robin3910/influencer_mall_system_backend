package com.macro.mall.mapper;

import com.macro.mall.model.WhResourceTags;
import com.macro.mall.model.WhResourceTagsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhResourceTagsMapper {
    long countByExample(WhResourceTagsExample example);

    int deleteByExample(WhResourceTagsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhResourceTags row);

    int insertSelective(WhResourceTags row);

    List<WhResourceTags> selectByExample(WhResourceTagsExample example);

    WhResourceTags selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhResourceTags row, @Param("example") WhResourceTagsExample example);

    int updateByExample(@Param("row") WhResourceTags row, @Param("example") WhResourceTagsExample example);

    int updateByPrimaryKeySelective(WhResourceTags row);

    int updateByPrimaryKey(WhResourceTags row);
}