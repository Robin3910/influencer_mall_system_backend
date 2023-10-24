package com.macro.mall.mapper;

import com.macro.mall.model.WhPlatforms;
import com.macro.mall.model.WhPlatformsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhPlatformsMapper {
    long countByExample(WhPlatformsExample example);

    int deleteByExample(WhPlatformsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhPlatforms row);

    int insertSelective(WhPlatforms row);

    List<WhPlatforms> selectByExample(WhPlatformsExample example);

    WhPlatforms selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhPlatforms row, @Param("example") WhPlatformsExample example);

    int updateByExample(@Param("row") WhPlatforms row, @Param("example") WhPlatformsExample example);

    int updateByPrimaryKeySelective(WhPlatforms row);

    int updateByPrimaryKey(WhPlatforms row);
}