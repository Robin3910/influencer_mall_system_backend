package com.macro.mall.mapper;

import com.macro.mall.model.WhCategories;
import com.macro.mall.model.WhCategoriesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhCategoriesMapper {
    long countByExample(WhCategoriesExample example);

    int deleteByExample(WhCategoriesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhCategories row);

    int insertSelective(WhCategories row);

    List<WhCategories> selectByExample(WhCategoriesExample example);

    WhCategories selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhCategories row, @Param("example") WhCategoriesExample example);

    int updateByExample(@Param("row") WhCategories row, @Param("example") WhCategoriesExample example);

    int updateByPrimaryKeySelective(WhCategories row);

    int updateByPrimaryKey(WhCategories row);
}