package com.macro.mall.mapper;

import com.macro.mall.model.WhUsers;
import com.macro.mall.model.WhUsersExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhUsersMapper {
    long countByExample(WhUsersExample example);

    int deleteByExample(WhUsersExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhUsers row);

    int insertSelective(WhUsers row);

    List<WhUsers> selectByExampleWithBLOBs(WhUsersExample example);

    List<WhUsers> selectByExample(WhUsersExample example);

    WhUsers selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhUsers row, @Param("example") WhUsersExample example);

    int updateByExampleWithBLOBs(@Param("row") WhUsers row, @Param("example") WhUsersExample example);

    int updateByExample(@Param("row") WhUsers row, @Param("example") WhUsersExample example);

    int updateByPrimaryKeySelective(WhUsers row);

    int updateByPrimaryKeyWithBLOBs(WhUsers row);

    int updateByPrimaryKey(WhUsers row);
}