package com.hu.video.service.impl;

import com.hu.video.mapper.AdminMapper;
import com.hu.video.service.IAdminEChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminEChartServiceImpl implements IAdminEChartService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public List<String> getFilmData() {
        return adminMapper.getFilmData();
    }

    @Override
    public List<Integer> getData() {
        return adminMapper.getData();
    }
}
