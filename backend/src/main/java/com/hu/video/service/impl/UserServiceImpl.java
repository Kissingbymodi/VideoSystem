package com.hu.video.service.impl;

import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.ResultDTO;
import com.hu.video.mapper.TUserMapper;
import com.hu.video.service.IUserService;
import com.hu.video.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private TUserMapper userMapper;

    @Override
    public ResultDTO<TUser> login(String userTel, String password) {
        Map<Object, String> map = new HashMap<>();
        map.put("userTel", userTel);
        map.put("password", password);
        TUser user = userMapper.queryByuserTelAndPwd(map);
        //判断user是否为空
        ResultDTO resultDTO = null;

        if (user == null) {
            resultDTO = ResultUtil.failResult(user, "手机号或密码为错误");
        } else {
            resultDTO = ResultUtil.successResult(user, "登录成功");
        }
        return resultDTO;
    }

    @Override
    public ResultDTO register(TUser tUser) {
        tUser.setRegisterDate(new Date());
        int c = userMapper.register(tUser);
        ResultDTO resultDTO = new ResultDTO();
        if(c == 0) {
            resultDTO.setResult(false);
            resultDTO.setMessage("注册失败");
        } else {
            resultDTO.setResult(true);
            resultDTO.setMessage("注册成功");
        }
        return resultDTO;
    }

    @Override
    public int updateUserIcon(String s, Long userId) {
        return userMapper.updateUserIcon(s, userId);
    }

    @Override
    public TUser getUserByUserId(Long userId) {
        return userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public void updateUserByUserId(TUser tUser) {
        userMapper.updateUserByUserId(tUser);
    }

    @Override
    public boolean queryByuserTel(String userTel) {
        List<TUser> userList = userMapper.queryByuserTel(userTel);
        return userList.size() == 0;
    }
}
