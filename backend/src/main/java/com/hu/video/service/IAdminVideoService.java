package com.hu.video.service;

import com.hu.video.entity.TState;
import com.hu.video.entity.TVideo;
import com.hu.video.entity.TVideotype;
import com.hu.video.entity.dto.UserStateDTO;
import com.hu.video.entity.dto.VideoInfoDTO;

import java.util.List;

public interface IAdminVideoService {


    List<VideoInfoDTO> videoList();

    int rdeleteVideo(Long id);

    List<VideoInfoDTO> underVideoList();

    int deleteVideo(Long id);

    int restoreVideo(Long id);

    VideoInfoDTO getVideoById(Long id);

    List<TState> getStateList();

    List<TVideotype> getVideoTypeList();

    int editVideo(TVideo video);

    List<VideoInfoDTO> getiVideoByTitle(String videoTitle);
}
