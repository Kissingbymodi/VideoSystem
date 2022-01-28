package com.hu.video.controller;

import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.ResultDTO;
import com.hu.video.service.IMessageService;
import com.hu.video.service.IUserService;
import com.hu.video.util.MsgResponse;
import com.hu.video.util.ValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("user")
public class VideoUserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMessageService messageService;

    /**
     * 实现路由跳转
     * @return
     */
    @RequestMapping("toLogin")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("toRegister")
    public String toRegister() {
        return "register";
    }

    @RequestMapping("toIndex")
    public String toIndex() {
        return "index";
    }

    @RequestMapping("toUEditorUser")
    public String toUEditorUser() {
        return "ueditUser";
    }


    @RequestMapping("toMessage")
    public String toMessage(){
        return "message";
    }


    /*---------检验手机号是否已存在--------*/
    @RequestMapping("checkTel")
    @ResponseBody
    public Map<Object, Boolean> checkTel(String userTel) {
        boolean flag = userService.queryByuserTel(userTel);
        Map<Object, Boolean> map = new HashMap<Object, Boolean>();
        System.out.println("flag" + flag);
        if (flag) {
            map.put("res", true);
        } else {
            map.put("res", false);
        }
        return map;
    }

    @RequestMapping("UEditorUser")
    public String UEditorUser(HttpSession session, TUser tUser, String preEncryptedProblem, Model model) {
        if(!tUser.getEncryptedProblem().equals(preEncryptedProblem)) {
            model.addAttribute("errorMsg", "当前密保不是最新密保");
            return "ueditUser";
        }
        session.removeAttribute("user");
        tUser.setIconUrl(userService.getUserByUserId(tUser.getUserId()).getIconUrl());
        session.setAttribute("user", tUser);
        System.out.println("tuser" + tUser);
        userService.updateUserByUserId(tUser);
        return "index";
    }

    /*---------上传头像--------*/
    @ResponseBody
    @RequestMapping("upload")
    public MsgResponse upload(@RequestParam MultipartFile file, HttpSession session) {
        String workplace = System.getProperty("user.dir");
        String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf("."));
        TUser user = (TUser) session.getAttribute("user");
        String fileName = "icon" + String.valueOf(user.getUserId()) + suffix;
        File newFile = new File(workplace + "/src/main/resources/static/uimages/" + fileName);
        userService.updateUserIcon("/user/getIcon/" + fileName,user.getUserId());
        try {
            file.transferTo(newFile);
            System.out.println("success");
            return MsgResponse.success("上传成功",null);
        } catch (IOException e) {
            e.printStackTrace();
            return MsgResponse.fail("上传失败");
        }
    }

    /*---------src getIcon--------*/
    @RequestMapping("/getIcon/{iconPath}")
    public void getIcon(HttpServletResponse response, @PathVariable String iconPath) throws IOException {
        String workpace = System.getProperty("user.dir");
        System.out.println(iconPath);

        File file = new File(workpace + "/src/main/resources/static/uimages/" + iconPath);

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

    /*----------用户注册-----------*/
    @RequestMapping("userRegister")
    @ResponseBody
    public ResultDTO register(TUser tUser) {
        tUser.setStateId(1);
        tUser.setFanNum(0);
        ResultDTO res = userService.register(tUser);
        return res;
    }

    /*----------用户登录-----------*/
    @RequestMapping("userLogin")
    @ResponseBody
    public ResultDTO login(String userTel, String password, String aCode, HttpSession session,Model model) {

        ResultDTO<TUser> res = userService.login(userTel, password);
        if (res.getResult()) {
            String randomCode = (String) session.getAttribute(ValidateCode.RANDOMCODEKEY);
            if (!aCode.equalsIgnoreCase(randomCode)) {
                //equalsIgnoreCase方法忽略大小写判断

                res.setMessage("验证码错误");
                res.setResult(false);
                return res;
            }
            int messageCount = messageService.msgCount(res.getData().getUserId());
            System.out.println(messageCount);
            session.setAttribute("messageCount", "(" + messageCount + ")");
            session.setAttribute("user", res.getData());
        }
        return res;
    }


    /*----------用户验证码验证-----------*/
    @RequestMapping("Img")
    public void Img(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("image/jpeg");
        //设置响应头，阻止浏览器缓存此响应的内容(节约内存)
        resp.setHeader("Pragma", "No-cache");
        //引用工具类中的方法，绘制验证码图片
        ValidateCode code = new ValidateCode();
        code.getValidateCode(req, resp);//调用绘制验证码的方法，绘制图片
    }

    @RequestMapping("logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        session.invalidate();
        return "index";
    }
}
