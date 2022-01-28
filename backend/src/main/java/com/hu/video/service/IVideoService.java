package com.hu.video.service;

import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;

import java.util.List;

public interface IVideoService {
    String addViewSum(Long videoId);

    TVideo getVideoByVideoId(Long videoId);

    List<TVideo> getRecommendVideo(int pageNum, int pageSize, Long userId);

    String thumbsUp(TUser user, Long videoId);

    String addComment(int starNum, Long userId, Long videoId);

    int addVideo(TVideo video);

    int updateVideoPath(String path, Long videoId);

    int updateVideoImage(String s, Long videoId);

    Long getVideoLastId(String videoTitle);

    int adminUpdateVideoImage(String s, Long videoId);

    List<TVideo> queryByVideoTitle(String videoTitle);

    List<TVideo> getVideoListByUserId(Long userId);

    List<TVideo> getVideoByVideoTypeId(Long videoTypeId);

    List<TVideo> getIndexRecommendVideo();

    List<TVideo> getIndexLastVideo();
}
