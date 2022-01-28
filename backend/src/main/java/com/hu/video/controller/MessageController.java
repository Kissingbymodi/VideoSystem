package com.hu.video.controller;

import com.hu.video.entity.TMessage;
import com.hu.video.entity.TMsgtype;
import com.hu.video.entity.TState;
import com.hu.video.entity.TUser;
import com.hu.video.service.IMessageService;
import com.hu.video.service.IMsgTypeService;
import com.hu.video.service.IStateService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("Message")
public class MessageController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMsgTypeService msgTypeService;

    @Autowired
    private IStateService stateService;

    @Autowired
    private IMessageService messageService;

    @RequestMapping("getMsgList")
    @ResponseBody
    public List<TMessage> getMsgListByAjax(HttpSession session, @RequestParam String msgTypeName) {
        return messageService.findMsgByMsgType((TUser)session.getAttribute("user"),msgTypeName);
    }

    @RequestMapping("delMsg")
    public MsgResponse delMsg(Long msgId) {
        return MsgResponse.success(messageService.deleteMsgById(msgId),null);
    }

    @RequestMapping("updateMsgState")
    public MsgResponse updateMsgState(Long msgId) {
        return MsgResponse.success(messageService.updateReadMessage(msgId, 7L), null);
    }

    @RequestMapping("addLetter")
    public MsgResponse addLetter(HttpSession session, @RequestParam String msgTitle, @RequestParam String msgContext, @RequestParam Long receiveUserId, @RequestParam Long msgTypeId) {
        TUser sendUser = (TUser) session.getAttribute("user");
        TMessage msg = new TMessage();
        String addMsg = null;
        if(sendUser != null) {
            msg.setMsgTitle(msgTitle);
            msg.setMsgContext(msgContext);
            msg.setSendUser(sendUser);
            TUser recieveUser = userService.getUserByUserId(receiveUserId);
            if(recieveUser == null) {
                addMsg = "接收用户为空";
                return  MsgResponse.fail(addMsg);
            }
            if(recieveUser.getUserId().equals(sendUser.getUserId())) {
                return MsgResponse.fail("不可对自己私信");
            }
            msg.setReceiveUser(recieveUser);
            TMsgtype msgType = msgTypeService.findOneByMsgTypeId(msgTypeId);
            msg.setMsgType(msgType);
            TState msgState = stateService.getStateByStateId(6L);
            msg.setMsgState(msgState);
            addMsg = messageService.addMsg(msg);
            return MsgResponse.success(addMsg, null);
        }
        addMsg = "发送用户为空";
        return MsgResponse.fail(addMsg);
    }
}
