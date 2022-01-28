package com.hu.video.mapper;

import com.hu.video.entity.TState;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TStateMapper {
    int deleteByPrimaryKey(Long stateId);

    int insert(TState record);

    int insertSelective(TState record);

    TState selectByPrimaryKey(Long stateId);

    int updateByPrimaryKeySelective(TState record);

    int updateByPrimaryKey(TState record);
}
