package com.hu.video.service;

import com.hu.video.entity.TUser;
import com.hu.video.entity.dto.ResultDTO;

public interface IUserService {
    ResultDTO<TUser> login(String userTel, String password);

    boolean queryByuserTel(String userTel);

    ResultDTO register(TUser tUser);

    void updateUserByUserId(TUser tUser);

    TUser getUserByUserId(Long userId);

    int updateUserIcon(String s, Long userId);
}
