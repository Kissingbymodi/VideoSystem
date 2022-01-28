package com.hu.video.service;

import com.hu.video.entity.TMsgtype;

public interface IMsgTypeService {
    TMsgtype findByTypeName(String msgTypeName);

    TMsgtype findOneByMsgTypeId(Long msgTypeId);
}
