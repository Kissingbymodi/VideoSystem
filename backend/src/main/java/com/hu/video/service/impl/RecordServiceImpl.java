package com.hu.video.service.impl;

import com.hu.video.entity.TVideo;
import com.hu.video.mapper.TRecordMapper;
import com.hu.video.mapper.TUserMapper;
import com.hu.video.service.IRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordServiceImpl implements IRecordService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TRecordMapper recordMapper;

    @Override
    public List<TVideo> getAllRecord(Long userId) {
        if (userMapper.selectByPrimaryKey(userId) != null) {
            List<TVideo> List = recordMapper.getRecordAll(userId);
            return List;
        }
        return null;
    }

    @Override
    public String deleteRecordById(Long userId, Long videoId) {
        if (userMapper.selectByPrimaryKey(userId) != null) {
            try {
                if (recordMapper.selectOne(userId, videoId) != null) {
                    recordMapper.deleteRecordByVideoId(userId, videoId);
                    return "记录删除成功";
                } else {
                    return "观看记录不存在";
                }
            } catch (Exception e) {
                return "记录删除失败";
            }
        }
        return "用户未登录";
    }

    @Override
    public String addRecord(Long userId, Long videoId) {
        if (userMapper.selectByPrimaryKey(userId) != null) {
            try {
                if (recordMapper.selectOne(userId, videoId) == null) {
                    recordMapper.insertOne(userId, videoId);
                    return "观看成功";
                } else {
                    return "记录已存在";
                }

            } catch (Exception e) {
                return "观看失败";
            }
        }
        return "用户未登录";
    }
}
