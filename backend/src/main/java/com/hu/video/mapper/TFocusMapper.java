package com.hu.video.mapper;

import com.hu.video.entity.TFocus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface TFocusMapper {
    int deleteByPrimaryKey(Long focusId);

    int insert(TFocus record);

    int insertSelective(TFocus record);

    TFocus selectByPrimaryKey(Long focusId);

    int updateByPrimaryKeySelective(TFocus record);

    int updateByPrimaryKey(TFocus record);

    int selectOneVerify(TFocus focus);

    List<Long> selectFocusedsId(Long userId);
}
