package com.hu.video.service.impl;

import com.hu.video.entity.Admin;
import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.UserStateDTO;
import com.hu.video.mapper.*;
import com.hu.video.service.IAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AdminUserServiceImpl implements IAdminUserService {

    @Autowired
    private TUserMapper userMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private TVideoMapper videoMapper;

    @Autowired
    private TCollectionMapper collectionMapper;

    @Autowired
    private TRecordMapper recordMapper;


    @Override
    public List<UserStateDTO> userList() {
        return userMapper.userList();
    }

    @Override
    public UserStateDTO getUserById(Long id) {
        return userMapper.getUserById(id);
    }

    @Override
    public void editUser(TUser tUser) {
        userMapper.updateUserByUserId(tUser);
    }

    @Override
    public void deleteUser(Long id) {
        userMapper.deleteByPrimaryKey(id);
        videoMapper.deleteByUserId(id);
        collectionMapper.deleteByUserId(id);
        recordMapper.deleteByUserId(id);
    }


    @Override
    public Admin login(Admin admin) {
        return adminMapper.login(admin);
    }

    @Override
    public List<UserStateDTO> getUserByName(String username) {
        return userMapper.getUserByName(username);
    }

    @Override
    public void addUser(TUser tUser) {
        tUser.setRegisterDate(new Date());
        tUser.setIconUrl("/user/getIcon/icon1.jpg");
        tUser.setStateId(1);
        tUser.setFanNum(0);
        userMapper.insertSelective(tUser);
    }


}
