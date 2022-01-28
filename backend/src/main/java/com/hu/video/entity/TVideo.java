package com.hu.video.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.List;

public class TVideo {
    @Override
    public String toString() {
        return "TVideo{" +
                "videoId=" + videoId +
                ", videoTitle='" + videoTitle + '\'' +
                ", videoInfo='" + videoInfo + '\'' +
                ", editDate=" + editDate +
                ", videoUrl='" + videoUrl + '\'' +
                ", thunmbnailUrl='" + thunmbnailUrl + '\'' +
                ", videoStateId=" + videoStateId +
                ", viewNum=" + viewNum +
                ", ppNum=" + ppNum +
                ", videoTypeId=" + videoTypeId +
                ", userId=" + userId +
                ", videoState=" + videoState +
                ", user=" + user +
                ", videoType=" + videoType +
                '}';
    }

    private Long videoId;

    private String videoTitle;

    private String videoInfo;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date editDate;

    private String videoUrl;

    private String thunmbnailUrl;

    private Long videoStateId;

    private Integer viewNum;

    private Integer ppNum;

    private Long videoTypeId;

    private Long userId;

    public TState getVideoState() {
        return videoState;
    }

    public void setVideoState(TState videoState) {
        this.videoState = videoState;
    }

    public TUser getUser() {
        return user;
    }

    public void setUser(TUser user) {
        this.user = user;
    }

    public TVideotype getVideoType() {
        return videoType;
    }

    public void setVideoType(TVideotype videoType) {
        this.videoType = videoType;
    }



    private TState videoState;
    private TUser user;
    private TVideotype videoType;

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
        this.videoTitle = videoTitle == null ? null : videoTitle.trim();
    }

    public String getVideoInfo() {
        return videoInfo;
    }

    public void setVideoInfo(String videoInfo) {
        this.videoInfo = videoInfo == null ? null : videoInfo.trim();
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl == null ? null : videoUrl.trim();
    }

    public String getThunmbnailUrl() {
        return thunmbnailUrl;
    }

    public void setThunmbnailUrl(String thunmbnailUrl) {
        this.thunmbnailUrl = thunmbnailUrl == null ? null : thunmbnailUrl.trim();
    }

    public Long getVideoStateId() {
        return videoStateId;
    }

    public void setVideoStateId(Long videoStateId) {
        this.videoStateId = videoStateId;
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

    public Long getVideoTypeId() {
        return videoTypeId;
    }

    public void setVideoTypeId(Long videoTypeId) {
        this.videoTypeId = videoTypeId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
