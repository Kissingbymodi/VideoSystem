package com.hu.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.video.entity.TState;
import com.hu.video.entity.TUser;
import com.hu.video.entity.TVideo;
import com.hu.video.entity.TVideotype;
import com.hu.video.entity.dto.VideoInfoDTO;
import com.hu.video.service.IAdminVideoService;
import com.hu.video.service.IStateService;
import com.hu.video.service.IVideoService;
import com.hu.video.service.IVideoTypeService;
import com.hu.video.util.DateUtil;
import com.hu.video.util.MsgResponse;
import com.hu.video.util.VueUtil;
import com.mysql.jdbc.log.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminVideoController {


    @Autowired
    private IAdminVideoService adminVideoService;

    @Autowired
    private IStateService stateService;

    @Autowired
    private IVideoService videoService;

    @Autowired
    private IVideoTypeService videoTypeService;

    private static String aVideoTitle;

    @RequestMapping("deleteVideo/{id}")
    public String deleteVideo(@PathVariable Long id) {
        adminVideoService.deleteVideo(id);
        return "success";
    }

    @RequestMapping("editVideo")
    public String editVideo(@RequestBody TVideo video) {
        adminVideoService.editVideo(video);
        return "success";
    }
    @RequestMapping("getVideoById")
    public VideoInfoDTO getVideoById(Long id) {
        return adminVideoService.getVideoById(id);
    }

    @RequestMapping("getStateList")
    public List<TState> getStateList() {
        return adminVideoService.getStateList();
    }

    @RequestMapping("getVideoTypeList")
    public List<TVideotype> getVideoTypeList() {
        return adminVideoService.getVideoTypeList();
    }


    @RequestMapping("restoreVideo/{id}")
    public String restoreVideo(@PathVariable Long id) {
        adminVideoService.restoreVideo(id);
        return "success";
    }

    @RequestMapping("rdeleteVideo/{id}")
    public String rdeleteVideo(@PathVariable Long id) {
        adminVideoService.rdeleteVideo(id);
        return "success";
    }
    @RequestMapping("addVideo")
    public String addVideo(@RequestParam String videoTitle,@RequestParam Long videoStateId,
                           @RequestParam String videoInfo, @RequestParam Long videoTypeId) {
        TUser user = new TUser();
        user.setUserId(1L);
        TVideo video = new TVideo();
        video.setUser(user);
        TState state = stateService.getStateByStateId(videoStateId);
        video.setVideoState(state);
        video.setVideoType(videoTypeService.getVideoTypeByVideoTypeId(videoTypeId));
        video.setVideoInfo(videoInfo);
        video.setVideoTitle(videoTitle);
        aVideoTitle = videoTitle;
        videoService.addVideo(video);
        return "200";
    }

    @RequestMapping(value = "searchVideo", method = RequestMethod.GET)
    public PageInfo<VideoInfoDTO> searchVideo(int pageNum, int pageSize, String videoName) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<VideoInfoDTO> videoInfoDTOs = adminVideoService.getiVideoByTitle(videoName);
        //获得 视频分页
        PageInfo<VideoInfoDTO> vidoePageInfo = new PageInfo<VideoInfoDTO>(videoInfoDTOs);
        return vidoePageInfo;
    }


    /**
     * 获得分页对象, pageNum是当前页数, pageSize是分页大小
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "videoPageInfo", method = RequestMethod.GET)
    public PageInfo<VideoInfoDTO> getPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<VideoInfoDTO> videoInfoDTOs = adminVideoService.videoList();
        //获得 视频分页
        PageInfo<VideoInfoDTO> vidoePageInfo = new PageInfo<VideoInfoDTO>(videoInfoDTOs);
        return vidoePageInfo;
    }

    @RequestMapping(value = "underVideoPageInfo", method = RequestMethod.GET)
    public PageInfo<VideoInfoDTO> underVideoPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //视频记录
        List<VideoInfoDTO> videoInfoDTOs = adminVideoService.underVideoList();
        //获得 视频分页
        PageInfo<VideoInfoDTO> vidoePageInfo = new PageInfo<VideoInfoDTO>(videoInfoDTOs);
        return vidoePageInfo;
    }

    @RequestMapping("adminVideoUpload")
    public MsgResponse adminVideoUpload(@RequestParam MultipartFile file) {
        String workplace = System.getProperty("user.dir");

        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        String path = "/static/video/" + DateUtil.currentDateFormatString() + suffix;
        File newFile = new File(workplace + "/src/main/resources" + path);
        Long videoId = videoService.getVideoLastId(aVideoTitle);
        videoService.updateVideoPath(path, videoId);
        try {
            file.transferTo(newFile);
            return MsgResponse.success("上传成功", "http://localhost:8081" + path);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    @RequestMapping("editThumbnailImageUpload")
    public VueUtil editThumbnailImageUpload(@RequestParam MultipartFile avatar, Long videoId) {
        String workplace = System.getProperty("user.dir");
        String suffix = avatar.getOriginalFilename().substring(avatar.getOriginalFilename().indexOf("."));
        String fileName = "video" + String.valueOf(videoId) + suffix;
        File newFile = new File(workplace + "/src/main/resources/static/vimages/" + fileName);
        videoService.adminUpdateVideoImage("/video/getVideoImage/" + fileName, videoId);
        try {
            avatar.transferTo(newFile);
            System.out.println("success");
            Object res = "http://localhost:8081/video/getVideoImage/video" + videoId + suffix;
            return VueUtil.success("上传成功", res);
        } catch (IOException e) {
            e.printStackTrace();
            return VueUtil.fail("上传失败");
        }
    }

    @RequestMapping("thumbnailImageupload")
    public VueUtil thumbnailImageupload(@RequestParam MultipartFile avatar) {
        String workplace = System.getProperty("user.dir");
        Long videoId = videoService.getVideoLastId(aVideoTitle);
        String suffix = avatar.getOriginalFilename().substring(avatar.getOriginalFilename().indexOf("."));
        String fileName = "video" + String.valueOf(videoId) + suffix;
        File newFile = new File(workplace + "/src/main/resources/static/vimages/" + fileName);
        videoService.adminUpdateVideoImage("/video/getVideoImage/" + fileName, videoId);
        try {
            avatar.transferTo(newFile);
            System.out.println("success");
            Object res = "http://localhost:8081/video/getVideoImage/video" + videoId + suffix;
            return VueUtil.success("上传成功", res);
        } catch (IOException e) {
            e.printStackTrace();
            return VueUtil.fail("上传失败");
        }
    }


}
