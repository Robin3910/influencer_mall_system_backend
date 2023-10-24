package com.macro.mall.mapper;

import com.macro.mall.model.WhResources;
import com.macro.mall.model.WhResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhResourcesMapper {
    long countByExample(WhResourcesExample example);

    int deleteByExample(WhResourcesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhResources row);

    int insertSelective(WhResources row);

    List<WhResources> selectByExampleWithBLOBs(WhResourcesExample example);

    List<WhResources> selectByExample(WhResourcesExample example);

    WhResources selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhResources row, @Param("example") WhResourcesExample example);

    int updateByExampleWithBLOBs(@Param("row") WhResources row, @Param("example") WhResourcesExample example);

    int updateByExample(@Param("row") WhResources row, @Param("example") WhResourcesExample example);

    int updateByPrimaryKeySelective(WhResources row);

    int updateByPrimaryKeyWithBLOBs(WhResources row);

    int updateByPrimaryKey(WhResources row);
}