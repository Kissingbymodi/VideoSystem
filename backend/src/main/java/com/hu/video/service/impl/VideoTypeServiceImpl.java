package com.hu.video.service.impl;

import com.hu.video.entity.TVideotype;
import com.hu.video.mapper.TVideotypeMapper;
import com.hu.video.service.IVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoTypeServiceImpl implements IVideoTypeService {

    @Autowired
    private TVideotypeMapper videotypeMapper;

    @Override
    public TVideotype getVideoTypeByTypeName(String typeName) {
        return videotypeMapper.getVideoTypeByTypeName(typeName);
    }

    @Override
    public List<TVideotype> getVideoTypeAll() {
        return videotypeMapper.getVideoTypeAll();
    }

    @Override
    public TVideotype getVideoTypeByVideoTypeId(Long videotypeId) {
        return videotypeMapper.selectByPrimaryKey(videotypeId);

    }
}
