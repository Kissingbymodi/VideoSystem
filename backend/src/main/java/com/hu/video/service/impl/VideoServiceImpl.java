package com.hu.video.service.impl;

import com.hu.video.entity.TMessage;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.entity.vo.CommentedStar;
import com.hu.video.mapper.*;
import com.hu.video.service.IMsgTypeService;
import com.hu.video.service.IStateService;
import com.hu.video.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements IVideoService {


    /*--------------调用mapper------------------*/
    @Autowired
    private TVideoMapper videoMapper;

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private TMessageMapper msgMapper;

    @Autowired
    private CommentedStarMapper commentedStarMapper;

    /*------------调用service---------------*/
    @Autowired
    private IMsgTypeService msgTypeMService;

    @Autowired
    private IStateService stateService;

    @Override
    public String addViewSum(Long videoId) {
        int signal = videoMapper.updateVideoViewSumByVideoId(videoId);
        if (signal == 1) {
            return "更新成功";
        } else {
            return "观看次数更新出错";
        }
    }


    @Override
    public List<TVideo> getVideoListByUserId(Long userId) {
        return videoMapper.getVideoListByUserId(userId,4L);
    }

    @Override
    public List<TVideo> getVideoByVideoTypeId(Long videoTypeId) {
        return videoMapper.getVideoByVideoTypeId(videoTypeId);
    }

    @Override
    public List<TVideo> getIndexLastVideo() {
        return videoMapper.getIndexLastVideo();
    }

    @Override
    public List<TVideo> getIndexRecommendVideo() {
        return videoMapper.getIndexRecommendVideo();
    }

    @Override
    public List<TVideo> queryByVideoTitle(String videoTitle) {
        return videoMapper.queryByVideoTitle(videoTitle);
    }

    @Override
    public int adminUpdateVideoImage(String s, Long videoId) {
        return videoMapper.adminUpdateVideoImage(s,videoId);
    }

    @Override
    public Long getVideoLastId(String videoTitle) {
        return videoMapper.getVideoLastId(videoTitle);
    }

    @Override
    public int updateVideoImage(String s, Long videoId) {
        return videoMapper.updateVideoImage(s,videoId);
    }

    @Override
    public int updateVideoPath(String path, Long videoId) {
        return videoMapper.updateVideoPath(path,videoId);
    }

    @Override
    public int addVideo(TVideo video) {
        return videoMapper.addVideo(video);
    }

    @Override
    public String addComment(int starNum, Long userId, Long videoId) {
        TVideo video = videoMapper.selectOneByVideoId(videoId);
        TUser user = userMapper.selectByPrimaryKey(video.getUser().getUserId());
        CommentedStar commentedStar = commentedStarMapper.getCommentedStar(userId, videoId);
        TMessage msg = new TMessage();
        if(commentedStar != null) {
            return "你已经参加点评";
        }
        if(user != null && video != null) {
            commentedStarMapper.insertOne(userId, videoId, starNum);
            msg.setMsgTitle("点评提醒");
            msg.setMsgContext("你好！你的主题为《" + video.getVideoTitle() + "》的视频获得用户【" + user.getUserName() + "】的" + starNum + "星点评");
            msg.setMsgType(msgTypeMService.findByTypeName("EvaluateNotice"));
            msg.setMsgState(stateService.getStateByStateId(6L));
            msg.setReceiveUser(video.getUser());
            msgMapper.insertMsg(msg);
            return "点评成功";
        }
        return "不可对自身视频点评";
    }

    @Override
    public String thumbsUp(TUser user, Long videoId) {
        TVideo video = this.getVideoByVideoId(videoId);
        TMessage msg = new TMessage();
        if (user != null && !user.getUserId().equals(video.getUser().getUserId())) {
            try {
                videoMapper.updateVideoPpSumByVideoId(videoId);
                msg.setMsgTitle("点赞提醒");
                msg.setMsgContext("你好! 你的视频《" + video.getVideoTitle() + "》的视频获得用户【" + user.getUserName() + "】的一枚点赞");
                msg.setMsgType(msgTypeMService.findByTypeName("SupportNotice"));
                msg.setMsgState(stateService.getStateByStateId(6L));
                msg.setReceiveUser(video.getUser());
                msgMapper.insertMsg(msg);
                return "点赞成功";
            } catch (Exception e) {
                return "点赞失败";
            }
        } else {
            return "不可对自己视频点赞";
        }
    }

    @Override
    public List<TVideo> getRecommendVideo(int curPage, int pageSize, Long userId) {
        int videoCount = videoMapper.selectVideoCount();
        int curIndex = (curPage - 1) * pageSize;
        if (curPage > 0 && pageSize > 0 && curIndex <= videoCount) {
            List<TVideo> videoList = videoMapper.selectRecommendVideo(curIndex, pageSize, userId);
            if (videoList != null) {
                return videoList;
            }
        }
        return null;
    }

    @Override
    public TVideo getVideoByVideoId(Long videoId) {
        if (videoId != 0) {
            TVideo video = videoMapper.selectOneByVideoId(videoId);
            if (video != null) {
                return video;
            }
        }
        return null;
    }
}
