package com.hu.video.service.impl;

import com.github.pagehelper.PageInfo;
import com.hu.video.entity.TMessage;
import com.hu.video.entity.TMsgtype;
import com.hu.video.entity.TUser;
import com.hu.video.mapper.TMessageMapper;
import com.hu.video.service.IMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements IMessageService {

    @Autowired
    private TMessageMapper msgMapper;

    @Override
    public int msgCount(Long userId) {
        return msgMapper.msgCount(userId);
    }

    @Override
    public String deleteMsgById(Long msgId) {
        try {
            TMessage message = msgMapper.selectByPrimaryKey(msgId);
            if(message == null) {
                return "删除数据不存在";
            }else {
                msgMapper.deleteByPrimaryKey(msgId);
                return "信息删除成功";
            }
        } catch (Exception e) {
            return "系统异常";
        }
    }

    @Override
    public String updateReadMessage(Long msgId, Long stateId) {
        try {
            TMessage message = msgMapper.selectByPrimaryKey(msgId);
            if(message == null) {
                return "更新数据不存在";
            }else {
                msgMapper.updateMessageState(msgId, stateId);
                return "信息已读";
            }
        } catch (Exception e) {
            return "系统异常";
        }
    }

    @Override
    public List<TMessage> findMsgByMsgType(TUser user, String msgTypeName) {
        if(user != null && msgTypeName != null && !msgTypeName.equals("")) {
            if(msgTypeName.equals("All")) {
                return  msgMapper.selectMsgByMsgType(user, null);
            } else {
                TMsgtype msgType = new TMsgtype();
                return msgMapper.selectMsgByMsgType(user, msgType);
            }
        }
        return null;
    }

    @Override
    public String addMsg(TMessage msg) {
        try {
            int flag = msgMapper.insertMsg(msg);
            if (flag == 1) {
                return "信息数据插入成功";
            }
            return "信息数据插入失败";
        }catch (Exception e) {
            e.printStackTrace();
            return "信息数据插入异常";
        }
    }
}
