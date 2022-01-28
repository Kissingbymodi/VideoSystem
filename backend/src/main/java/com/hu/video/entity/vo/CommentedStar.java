package com.hu.video.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentedStar {

    private Long commentedStarId;

    private Long userId;

    private Long videoId;

    private int starNum;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date commentDate;

    public Long getCommentedStarId() {
        return commentedStarId;
    }

    public void setCommentedStarId(Long commentedStarId) {
        this.commentedStarId = commentedStarId;
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

    public int getStarNum() {
        return starNum;
    }

    public void setStarNum(int starNum) {
        this.starNum = starNum;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
