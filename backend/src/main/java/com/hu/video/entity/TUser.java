package com.hu.video.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TUser {
    private Long userId;

    private String userName;

    private Integer userAge;

    private String userSex;

    private String password;

    @Override
    public String toString() {
        return "TUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userAge=" + userAge +
                ", userSex='" + userSex + '\'' +
                ", password='" + password + '\'' +
                ", encryptedProblem='" + encryptedProblem + '\'' +
                ", fanNum=" + fanNum +
                ", userTel='" + userTel + '\'' +
                ", registerDate=" + registerDate +
                ", iconUrl='" + iconUrl + '\'' +
                ", stateId=" + stateId +
                '}';
    }

    private String encryptedProblem;


    private Integer fanNum;

    private String userTel;

    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date registerDate;

    private String iconUrl;

    private Integer stateId;

    public String getEncryptedProblem() {
        return encryptedProblem;
    }

    public void setEncryptedProblem(String encryptedProblem) {
        this.encryptedProblem = encryptedProblem;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex == null ? null : userSex.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getFanNum() {
        return fanNum;
    }

    public void setFanNum(Integer fanNum) {
        this.fanNum = fanNum;
    }

    public String getUserTel() {
        return userTel;
    }

    public void setUserTel(String userTel) {
        this.userTel = userTel == null ? null : userTel.trim();
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public Integer getStateId() {
        return stateId;
    }

    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
}
