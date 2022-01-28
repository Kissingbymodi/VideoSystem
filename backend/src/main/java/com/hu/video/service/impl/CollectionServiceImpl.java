package com.hu.video.service.impl;

import com.hu.video.entity.TMessage;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.mapper.TCollectionMapper;
import com.hu.video.mapper.TMessageMapper;
import com.hu.video.mapper.TMsgtypeMapper;
import com.hu.video.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements ICollectionService {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMsgTypeService msgTypeService;

    @Autowired
    private IStateService stateService;

    @Autowired
    private TCollectionMapper collectionMapper;

    @Autowired
    private TMessageMapper msgMapper;

    @Override
    public String addCollection(Long userId, Long videoId) {
        TVideo video = videoService.getVideoByVideoId(videoId);
        TUser user = userService.getUserByUserId(userId);
        System.out.println(user.getUserId() + " " + video.getUser().getUserId());
        if (user != null && !user.getUserId().equals(video.getUser().getUserId())) {
            try {
                if (collectionMapper.selectOne(userId, videoId) == null) {
                    collectionMapper.insertOne(userId, videoId);
                    TMessage msg = new TMessage();
                    msg.setMsgTitle("收藏提醒");
                    msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频,被用户【" + user.getUserName() + "】的收藏");
                    msg.setMsgType(msgTypeService.findByTypeName("CollectionNotice"));
                    msg.setMsgState(stateService.getStateByStateId(6L));
                    msg.setReceiveUser(video.getUser());
                    msgMapper.insertMsg(msg);
                    return "收藏成功";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "收藏失败";
            }
        }
        return "不可对自己视频进行收藏";
    }

    @Override
    public String deleteCollection(Long userId, Long videoId) {
        if (userService.getUserByUserId(userId) != null) {
            try {
                if (collectionMapper.selectOne(userId, videoId) != null) {
                    collectionMapper.deleteCollectionByVideoId(userId, videoId);
                    return "收藏删除成功";
                } else {
                    return "收藏记录不存在";
                }
            } catch (Exception e) {
                e.printStackTrace();
                return "收藏删除失败";
            }
        }
        return "用户未登录";
    }

    @Override
    public List<TVideo> getAllCollection(Long userId) {
        if (userService.getUserByUserId(userId) != null) {
            return collectionMapper.getCollettionAll(userId);
        }
        return null;
    }

    @Override
    public String vertifyIfCollection(Long userId, Long videoId) {
        if (userService.getUserByUserId(userId) != null) {
            try {
                if (collectionMapper.selectOne(userId, videoId) == null) {
                    return "未收藏";
                }
                return "已收藏";
            } catch (Exception e) {
                return "收藏失败";
            }
        }
        return "用户未登录";
    }
}
