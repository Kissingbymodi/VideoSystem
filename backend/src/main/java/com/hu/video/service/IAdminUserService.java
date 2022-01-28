package com.hu.video.service;

import com.hu.video.entity.Admin;
import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.UserStateDTO;

import java.util.List;

public interface IAdminUserService {
    List<UserStateDTO> userList();

    UserStateDTO getUserById(Long id);

    void editUser(TUser tUser);

    void deleteUser(Long id);

    void addUser(TUser tUser);

    Admin login(Admin admin);

    List<UserStateDTO> getUserByName(String username);
}
