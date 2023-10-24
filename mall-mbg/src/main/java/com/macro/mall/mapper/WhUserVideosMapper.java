package com.macro.mall.mapper;

import com.macro.mall.model.WhUserVideos;
import com.macro.mall.model.WhUserVideosExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WhUserVideosMapper {
    long countByExample(WhUserVideosExample example);

    int deleteByExample(WhUserVideosExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WhUserVideos row);

    int insertSelective(WhUserVideos row);

    List<WhUserVideos> selectByExampleWithBLOBs(WhUserVideosExample example);

    List<WhUserVideos> selectByExample(WhUserVideosExample example);

    WhUserVideos selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") WhUserVideos row, @Param("example") WhUserVideosExample example);

    int updateByExampleWithBLOBs(@Param("row") WhUserVideos row, @Param("example") WhUserVideosExample example);

    int updateByExample(@Param("row") WhUserVideos row, @Param("example") WhUserVideosExample example);

    int updateByPrimaryKeySelective(WhUserVideos row);

    int updateByPrimaryKeyWithBLOBs(WhUserVideos row);

    int updateByPrimaryKey(WhUserVideos row);
}