package com.hu.video.entity;


import java.util.Date;

public class TMessage {
    private Long msgId;

    private String msgTitle;

    private String msgContext;

    private Date msgSendDate;

    private Long msgSendUserId;

    private Long msgReceiveUserId;

    private TUser sendUser;

    private TUser receiveUser;

    private TState msgState;

    private TMsgtype msgType;

    public TUser getSendUser() {
        return sendUser;
    }

    public void setSendUser(TUser sendUser) {
        this.sendUser = sendUser;
    }

    public TUser getReceiveUser() {
        return receiveUser;
    }

    public void setReceiveUser(TUser receiveUser) {
        this.receiveUser = receiveUser;
    }

    public TState getMsgState() {
        return msgState;
    }

    public void setMsgState(TState msgState) {
        this.msgState = msgState;
    }

    public TMsgtype getMsgType() {
        return msgType;
    }

    public void setMsgType(TMsgtype msgType) {
        this.msgType = msgType;
    }

    private Long msgStateId;

    private Long msgtypeId;

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgContext() {
        return msgContext;
    }

    public void setMsgContext(String msgContext) {
        this.msgContext = msgContext == null ? null : msgContext.trim();
    }

    public Date getMsgSendDate() {
        return msgSendDate;
    }

    public void setMsgSendDate(Date msgSendDate) {
        this.msgSendDate = msgSendDate;
    }

    public Long getMsgSendUserId() {
        return msgSendUserId;
    }

    public void setMsgSendUserId(Long msgSendUserId) {
        this.msgSendUserId = msgSendUserId;
    }

    public Long getMsgReceiveUserId() {
        return msgReceiveUserId;
    }

    public void setMsgReceiveUserId(Long msgReceiveUserId) {
        this.msgReceiveUserId = msgReceiveUserId;
    }

    public Long getMsgStateId() {
        return msgStateId;
    }

    public void setMsgStateId(Long msgStateId) {
        this.msgStateId = msgStateId;
    }

    public Long getMsgtypeId() {
        return msgtypeId;
    }

    public void setMsgtypeId(Long msgtypeId) {
        this.msgtypeId = msgtypeId;
    }
}
