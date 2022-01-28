package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.service.ICollectionService;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("collection")
public class CollectionController {

    @Autowired
    private ICollectionService collectionService;

    @RequestMapping("addCollection")
    public MsgResponse addCollection(HttpSession session, String videoId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null && videoId.matches("[0-9]*")) {
            return MsgResponse.success(collectionService.addCollection(user.getUserId(), Long.parseLong(videoId)), null);
        }
        return MsgResponse.fail("用户未登录或者不可对自己视频进行收藏");
    }

    @RequestMapping("vertifyIfCollection")
    public MsgResponse vertifyIfCollection(HttpSession session, String videoId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null && videoId.matches("[0-9]*")) {
            return MsgResponse.success(collectionService.vertifyIfCollection(user.getUserId(), Long.parseLong(videoId)), null);
        }
        return MsgResponse.fail("用户未登录");
    }

    @RequestMapping("getAllCollection")
    @ResponseBody
    public MsgResponse getAllCollection(HttpSession session) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null){
            List<TVideo> collection = collectionService.getAllCollection(user.getUserId());
            if(collection != null) {
                return MsgResponse.success("获取收藏列表成功", collection);
            }
            else {
                return MsgResponse.fail("获取收藏列表失败");
            }
        }else {
            return MsgResponse.fail("当前用户不存在，请登录！");
        }
    }

    @RequestMapping("deleteCollection")
    @ResponseBody
    public MsgResponse deleteCollection(HttpSession session, Long videoId) {
        TUser user = (TUser) session.getAttribute("user");
        if(user != null) {
            return MsgResponse.success(collectionService.deleteCollection(user.getUserId(), videoId),null);
        }else {
            return MsgResponse.fail("用户未登录或视频ID错误");
        }
    }
}
