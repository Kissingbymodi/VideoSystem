package com.hu.video.entity;

public class TPredict {
    private Long preId;

    private Long userId;

    private Long videoId;

    private Float preStar;

    public Long getPreId() {
        return preId;
    }

    public void setPreId(Long preId) {
        this.preId = preId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public Float getPreStar() {
        return preStar;
    }

    public void setPreStar(Float preStar) {
        this.preStar = preStar;
    }
}