package com.macro.mall.mapper;

import com.macro.mall.model.WhResourceItems;
import com.macro.mall.model.WhResourceItemsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhResourceItemsMapper {
    long countByExample(WhResourceItemsExample example);

    int deleteByExample(WhResourceItemsExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhResourceItems row);

    int insertSelective(WhResourceItems row);

    List<WhResourceItems> selectByExampleWithBLOBs(WhResourceItemsExample example);

    List<WhResourceItems> selectByExample(WhResourceItemsExample example);

    WhResourceItems selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhResourceItems row, @Param("example") WhResourceItemsExample example);

    int updateByExampleWithBLOBs(@Param("row") WhResourceItems row, @Param("example") WhResourceItemsExample example);

    int updateByExample(@Param("row") WhResourceItems row, @Param("example") WhResourceItemsExample example);

    int updateByPrimaryKeySelective(WhResourceItems row);

    int updateByPrimaryKeyWithBLOBs(WhResourceItems row);

    int updateByPrimaryKey(WhResourceItems row);
}