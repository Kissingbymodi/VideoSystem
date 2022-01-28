package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.socketserver.BarrageWebSocket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("barrage")
public class BarrageController {

    @RequestMapping("sendGroupMessage/{videoId}")
    public Map<String, Object> groupMessage(HttpSession session, @PathVariable Integer videoId, @RequestParam String message) {
        Map<String, Object> res = new HashMap<>();
        TUser sendUser = (TUser) session.getAttribute("user");
        if (sendUser != null && !message.equals("")) {
            // 接受消息message 进行组发
            BarrageWebSocket.sendGroupMessage(sendUser.getUserName(), videoId, message);
            res.put("code", 200);
            res.put("msg", "success");
        }
        res.put("code", 500);
        res.put("msg", "error");
        return res;
    }
}
