package com.hu.video.controller;

import com.hu.video.entity.TState;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.entity.TVideotype;
import com.hu.video.service.*;
import com.hu.video.util.DateUtil;
import com.hu.video.util.MsgResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;

// url : 127.0.0.1/video?vidoeId=1
@RequestMapping("video")
@Controller
public class VideoController {

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IRecordService recordService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IVideoTypeService videoTypeService;

    @Autowired
    private IStateService stateService;

    private static String uVideoTitle;

    @RequestMapping("toVideoCenter")
    public String toVideoCenter() {
        return "videoCenter";
    }

    @RequestMapping("getVideoByType")
    @ResponseBody
    public List<TVideo> getVideoByType(Long videoTypeId) {
        return videoService.getVideoByVideoTypeId(videoTypeId);
    }


    @RequestMapping("getIndexLastVideo")
    @ResponseBody
    public List<TVideo> getIndexLastVideo() {
        List<TVideo> indexLastVideo = videoService.getIndexLastVideo();
        System.out.println("size " + indexLastVideo.size());
        return indexLastVideo;
    }

    @RequestMapping("getVideoListByUserId")
    @ResponseBody
    public MsgResponse getVideoListByUserId(Long userId) {
        List<TVideo> videoList = videoService.getVideoListByUserId(userId);
        if (videoList != null) {
            return MsgResponse.success("success", videoList);
        }
        return MsgResponse.fail("fail");
    }

    @RequestMapping("search")
    public String searchVideo(String videoTitle, Model model) {
        List<TVideo> videoList = videoService.queryByVideoTitle(videoTitle);
        model.addAttribute("videoList", videoList);
        return "genre";
    }

    @RequestMapping("toAddVideo")
    public String toAddVideo(Model model) {
        List<TVideotype> videoTypes = videoTypeService.getVideoTypeAll();
        model.addAttribute("videoTypeList", videoTypes);
        return "uaddVideo";
    }


    @RequestMapping(value = "uAddVideo",method = RequestMethod.POST)
    @ResponseBody
    public String uAddVideo(HttpSession session, @RequestParam String videoTitle,
                            @RequestParam String videoInfo, @RequestParam String typeName) {
        TUser tUser = (TUser) session.getAttribute("user");
        TVideo video = new TVideo();
        video.setUser(tUser);
        TState state = stateService.getStateByStateId(5L);
        video.setVideoState(state);
        video.setVideoType(videoTypeService.getVideoTypeByTypeName(typeName));
        video.setVideoInfo(videoInfo);
        video.setVideoTitle(videoTitle);
        uVideoTitle = videoTitle;
        videoService.addVideo(video);
        return "200";
    }

    /*---------上传视频--------*/
    @RequestMapping("uploadVideo")
    @ResponseBody
    public MsgResponse uploadVideo(@RequestParam MultipartFile file) {
        String workplace = System.getProperty("user.dir");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String path = "/static/video/" + DateUtil.currentDateFormatString() + suffix;
        File newFile = new File(workplace + "/src/main/resources" + path);
        Long videoId = videoService.getVideoLastId(uVideoTitle);
        videoService.updateVideoPath(path, videoId);
        try {
            file.transferTo(newFile);
            return MsgResponse.success("上传成功",null);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    /*---------上传视频图片--------*/
    @ResponseBody
    @RequestMapping("uploadImage")
    public MsgResponse uploadImage(@RequestParam MultipartFile file, HttpSession session) {
        String workplace = System.getProperty("user.dir");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
//        TUser user = (TUser) session.getAttribute("user");
        Long videoId = videoService.getVideoLastId(uVideoTitle);
        String fileName = "video" + videoId + suffix;
        File newFile = new File(workplace + "/src/main/resources/static/vimages/" + fileName);
        videoService.updateVideoImage("/video/getVideoImage/" + fileName, videoId);
        try {
            file.transferTo(newFile);
            System.out.println("success");
            return MsgResponse.success("上传成功",null);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }
    /*---------get视频图片--------*/
    @RequestMapping("/getVideoImage/{iconPath}")
    public void getVideoImage(HttpServletResponse response, @PathVariable String iconPath) throws IOException {
        String workpace = System.getProperty("user.dir");
        File file = new File(workpace + "/src/main/resources/static/vimages/" + iconPath);
        InputStream in = new FileInputStream(file);
        OutputStream out = response.getOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while((len = in.read(buffer)) != -1) {
            out.write(buffer, 0, len);
        }
        in.close();
        out.close();
    }

    @RequestMapping("getRecommendVideo")
    @ResponseBody
    public List<TVideo> getRecommendVideo(HttpServletRequest request) {
        TUser user = (TUser) request.getSession().getAttribute("user");
        return videoService.getRecommendVideo(1, 6, user.getUserId());
    }

    @RequestMapping("getIndexRecommendVideo")
    @ResponseBody
    public List<TVideo> getIndexRecommendVideo() {
        return videoService.getIndexRecommendVideo();
    }

    @RequestMapping("videoPlay")
    public String videoPlay(String videoId, HttpServletResponse response, HttpSession session, Model model) throws IOException {

        Object obj = session.getAttribute("user");
        TVideo s = null;
        if (obj != null && videoId != null && !videoId.equals("")) {
            Long videoId2 = Long.parseLong(videoId);
            // 因为要观看要记录，故要增加观看次数
            String result = videoService.addViewSum(videoId2);
            TVideo video = videoService.getVideoByVideoId(videoId2);
            // 添加观看记录
            recordService.addRecord(((TUser) obj).getUserId(), videoId2);
            model.addAttribute("result", result);
            if (video != null) {
                TUser user = userService.getUserByUserId(video.getUser().getUserId());
                video.setUser(user);
                TVideotype videoType = videoTypeService.getVideoTypeByVideoTypeId(video.getVideoType().getVideotypeId());
                video.setVideoType(videoType);
                session.setAttribute("curVideo", video);
            }
        }
        return "videoplay";

    }

    @RequestMapping("thumbsUp")
    @ResponseBody
    public MsgResponse thumbsUp(HttpSession session, @RequestParam String videoId) {
        TUser user = (TUser) session.getAttribute("user");
        String msg = videoService.thumbsUp(user, Long.parseLong(videoId));
        if (msg.equals("点赞成功")) {
            return MsgResponse.success(msg, null);
        }
        return MsgResponse.fail(msg);
    }

    @RequestMapping("commented")
    @ResponseBody
    public MsgResponse commented(@RequestParam int starNum, @RequestParam Long userId, @RequestParam Long videoId) {
        return MsgResponse.success(videoService.addComment(starNum, userId, videoId), null);
    }
}
