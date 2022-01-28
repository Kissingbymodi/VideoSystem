package com.hu.video.entity.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hu.video.entity.TMsgtype;
import com.hu.video.entity.TState;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideotype;

import java.util.Date;

public class VideoInfoDTO {

    private Long videoId;

    private String videoTitle;

    private String videoInfo;

    private TVideotype videoType;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editDate;

    private TUser tUser;

    private TState videoState;

    private String videoUrl;

    private String thumbnailUrl;

    private Integer viewNum;

    private Integer ppNum;

    private Long videoTypeId;

    private Long videoStateId;


    public VideoInfoDTO(Long videoId, String videoTitle, String videoInfo, TVideotype videoType, Date editDate, TUser tUser, TState videoState, String videoUrl, String thumbnailUrl, Integer viewNum, Integer ppNum, Long videoTypeId, Long videoStateId) {
        this.videoId = videoId;
        this.videoTitle = videoTitle;
        this.videoInfo = videoInfo;
        this.videoType = videoType;
        this.editDate = editDate;
        this.tUser = tUser;
        this.videoState = videoState;
        this.videoUrl = videoUrl;
        this.thumbnailUrl = thumbnailUrl;
        this.viewNum = viewNum;
        this.ppNum = ppNum;
        this.videoTypeId = videoTypeId;
        this.videoStateId = videoStateId;
    }


    public Long getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(Long videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public Long getVideoStateId() {
        return videoStateId;
    }

    public void setVideoStateId(Long videoStateId) {
        this.videoStateId = videoStateId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getVideoTitle() {
        return videoTitle;
    }

    public void setVideoTitle(String videoTitle) {
        this.videoTitle = videoTitle;
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo;
    }

    public TVideotype getVideoType() {
        return videoType;
    }

    public void setVideoType(TVideotype videoType) {
        this.videoType = videoType;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public TUser gettUser() {
        return tUser;
    }

    public void settUser(TUser tUser) {
        this.tUser = tUser;
    }

    public TState getVideoState() {
        return videoState;
    }

    public void setVideoState(TState videoState) {
        this.videoState = videoState;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Integer getViewNum() {
        return viewNum;
    }

    public void setViewNum(Integer viewNum) {
        this.viewNum = viewNum;
    }

    public Integer getPpNum() {
        return ppNum;
    }

    public void setPpNum(Integer ppNum) {
        this.ppNum = ppNum;
    }

    public VideoInfoDTO() {
    }


}
