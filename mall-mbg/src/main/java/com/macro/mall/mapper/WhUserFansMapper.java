package com.macro.mall.mapper;

import com.macro.mall.model.WhUserFans;
import com.macro.mall.model.WhUserFansExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhUserFansMapper {
    long countByExample(WhUserFansExample example);

    int deleteByExample(WhUserFansExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhUserFans row);

    int insertSelective(WhUserFans row);

    List<WhUserFans> selectByExampleWithBLOBs(WhUserFansExample example);

    List<WhUserFans> selectByExample(WhUserFansExample example);

    WhUserFans selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhUserFans row, @Param("example") WhUserFansExample example);

    int updateByExampleWithBLOBs(@Param("row") WhUserFans row, @Param("example") WhUserFansExample example);

    int updateByExample(@Param("row") WhUserFans row, @Param("example") WhUserFansExample example);

    int updateByPrimaryKeySelective(WhUserFans row);

    int updateByPrimaryKeyWithBLOBs(WhUserFans row);

    int updateByPrimaryKey(WhUserFans row);
}