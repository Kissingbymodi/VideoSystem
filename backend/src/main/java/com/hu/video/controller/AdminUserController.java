package com.hu.video.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hu.video.entity.Admin;
import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.UserStateDTO;
import com.hu.video.service.IAdminUserService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import com.hu.video.util.VueUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("admin")
@RestController
public class AdminUserController {

    @Autowired
    private IAdminUserService adminUserService;

    @Autowired
    private IUserService userService;

    @RequestMapping("login")
    public MsgResponse login(@RequestBody Admin admin, HttpSession session) {
        Admin admin1 = adminUserService.login(admin);
        if (admin1 == null) {
            return MsgResponse.fail("密码错误");
        }
        session.setAttribute("admin", admin);
        return MsgResponse.success("登录成功", null);
    }

    @RequestMapping("list")
    public List<UserStateDTO> userList() {
        return adminUserService.userList();
    }

    @RequestMapping("getUserById")
    public UserStateDTO getUserById(Long id) {
        return adminUserService.getUserById(id);
    }

    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    public void editUser(@RequestBody TUser tUser) {
        adminUserService.editUser(tUser);
    }

    @RequestMapping("deleteUser/{id}")
    public String deleteUser(@PathVariable Long id) {
        adminUserService.deleteUser(id);
        return "success";
    }

    @RequestMapping(value = "addUser", method = RequestMethod.POST)
    public String addUser(@RequestBody TUser tUser) {
        adminUserService.addUser(tUser);
        return "success";
    }

    /*---------上传头像--------*/
    @ResponseBody
    @RequestMapping("upload")
    public VueUtil upload(@RequestParam MultipartFile avatar, Long userId) {
        String workplace = System.getProperty("user.dir");
        //获取上传时候的文件名
        String suffix = avatar.getOriginalFilename().substring(avatar.getOriginalFilename().indexOf("."));
        String fileName = "icon" + String.valueOf(userId) + suffix;
        File newFile = new File(workplace + "/src/main/resources/static/uimages/" + fileName);
        userService.updateUserIcon("/user/getIcon/" + fileName,userId);
        try {
            avatar.transferTo(newFile);
            System.out.println("success");
            Object res = "http://localhost:8081/user/getIcon/icon" + userId + suffix;
            System.out.println(res);
            return VueUtil.success("上传成功",res);
        } catch (IOException e) {
            e.printStackTrace();
            return VueUtil.fail("上传失败");
        }
    }

    @RequestMapping(value = "searchUser", method = RequestMethod.GET)
    public PageInfo<UserStateDTO> searchUser(int pageNum, int pageSize,String username) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //用户记录
        List<UserStateDTO> userStateDTOS = adminUserService.getUserByName(username);
        //获得 用户分页
        PageInfo<UserStateDTO> pageInfo = new PageInfo<UserStateDTO>(userStateDTOS);
        return pageInfo;
    }

    /**
     * 获得分页对象, pageNum是当前页数, pageSize是分页大小
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "pageInfo", method = RequestMethod.GET)
    public PageInfo<UserStateDTO> getPageInfo(int pageNum, int pageSize) {
        //调用一个pageHelper的一个静态方法
        PageHelper.startPage(pageNum, pageSize);
        //用户记录
        List<UserStateDTO> userStateDTOS = adminUserService.userList();
        //获得 用户分页
        PageInfo<UserStateDTO> pageInfo = new PageInfo<UserStateDTO>(userStateDTOS);
        return pageInfo;
    }
}
