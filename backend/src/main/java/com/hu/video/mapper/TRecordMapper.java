package com.hu.video.mapper;

import com.hu.video.entity.TRecord;
import com.hu.video.entity.TVideo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TRecordMapper {
    int deleteByPrimaryKey(Long recordId);

    int insert(TRecord record);

    int insertSelective(TRecord record);

    TRecord selectByPrimaryKey(Long recordId);

    int updateByPrimaryKeySelective(TRecord record);

    int updateByPrimaryKey(TRecord record);

    TRecord selectOne(@Param("userId") Long userId, @Param("videoId") Long videoId);

    int insertOne(@Param(value="userId")Long userId,@Param(value="videoId")Long videoId);

    List<TVideo> getRecordAll(Long userId);

    int deleteRecordByVideoId(@Param("userId") Long userId, @Param("videoId") Long videoId);

    void deleteByUserId(Long id);
}
