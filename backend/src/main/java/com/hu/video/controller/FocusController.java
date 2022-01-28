package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.service.IFocusService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("focus")
public class FocusController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IFocusService focusService;

    @RequestMapping(value = "focusVerify", method = RequestMethod.POST)
    public MsgResponse focusVerify(HttpSession session, String focusedId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long userId = user.getUserId();
            Long focusedIdLong = Long.parseLong(focusedId);
            return MsgResponse.success(focusService.focusVerify(userId, focusedIdLong), null);
        }
        return MsgResponse.fail("参数错误");
    }

    @RequestMapping("focusOn")
    public MsgResponse focusOn(HttpSession session, String focusedId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null && focusedId != null && !focusedId.equals("")) {
            Long userId = user.getUserId();
            Long focusedIdLong = Long.parseLong(focusedId);
            return MsgResponse.success(focusService.addFocused(userId, focusedIdLong),null);
        }
        return MsgResponse.fail("参数错误");
    }

    @RequestMapping("focusedList")
    public MsgResponse focusedList(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if(user!=null) {
            Long userId = user.getUserId();
            List<Long> userFocusList = focusService.getUserFocusList(userId);
            List<TUser> focusList= new ArrayList<TUser>();
            for(Long focusId : userFocusList) {
                focusList.add(userService.getUserByUserId(focusId));
            }

            return MsgResponse.success("success", focusList);
        }else {
            return MsgResponse.fail("参数错误");
        }
    }
}
