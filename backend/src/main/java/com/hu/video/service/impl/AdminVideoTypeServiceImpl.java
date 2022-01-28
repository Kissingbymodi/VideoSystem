package com.hu.video.service.impl;

import com.hu.video.entity.TVideotype;
import com.hu.video.mapper.TVideotypeMapper;
import com.hu.video.service.IAdminVideoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminVideoTypeServiceImpl implements IAdminVideoTypeService {

    @Autowired
    private TVideotypeMapper videotypeMapper;
    @Override
    public List<TVideotype> categoryList() {
        return videotypeMapper.getVideoTypeAll();
    }

    @Override
    public List<TVideotype> getVideoTypeByName(String typeName) {
        return videotypeMapper.getVideoTypeByName(typeName);
    }

    @Override
    public boolean addVideoType(TVideotype videotype) {
        return videotypeMapper.insertSelective(videotype) > 0;
    }

    @Override
    public TVideotype getCategoryById(Long id) {
        return videotypeMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteCategoryById(Long id) {
        return videotypeMapper.deleteByPrimaryKey(id) > 0;
    }
}
