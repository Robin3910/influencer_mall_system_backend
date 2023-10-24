package com.macro.mall.mapper;

import com.macro.mall.model.WhRegions;
import com.macro.mall.model.WhRegionsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhRegionsMapper {
    long countByExample(WhRegionsExample example);

    int deleteByExample(WhRegionsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhRegions row);

    int insertSelective(WhRegions row);

    List<WhRegions> selectByExampleWithBLOBs(WhRegionsExample example);

    List<WhRegions> selectByExample(WhRegionsExample example);

    WhRegions selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhRegions row, @Param("example") WhRegionsExample example);

    int updateByExampleWithBLOBs(@Param("row") WhRegions row, @Param("example") WhRegionsExample example);

    int updateByExample(@Param("row") WhRegions row, @Param("example") WhRegionsExample example);

    int updateByPrimaryKeySelective(WhRegions row);

    int updateByPrimaryKeyWithBLOBs(WhRegions row);

    int updateByPrimaryKey(WhRegions row);
}