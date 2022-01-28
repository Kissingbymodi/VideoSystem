package com.hu.video.service.impl;

import com.hu.video.entity.TFocus;
import com.hu.video.mapper.TFocusMapper;
import com.hu.video.service.IFocusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FocusServiceImpl implements IFocusService {

    @Autowired
    private TFocusMapper focusMapper;

    @Override
    public String focusVerify(Long userId, Long focusedIdLong) {
        TFocus focus = new TFocus();
        focus.setUserId(userId);
        focus.setFocusId(focusedIdLong);
        int flag = focusMapper.selectOneVerify(focus);
        if(flag == 0)
            return "未关注";
        return "已关注";
    }

    @Override
    public List<Long> getUserFocusList(Long userId) {
        return focusMapper.selectFocusedsId(userId);
    }

    @Override
    public String addFocused(Long userId, Long focusedIdLong) {
        //不可对自己关注
        if(userId.equals(focusedIdLong)) {
            return "不可对自己进行关注";
        }
        TFocus focus = new TFocus();
        focus.setUserId(userId);
        focus.setFocusedId(focusedIdLong);
        int flag = focusMapper.selectOneVerify(focus);
        if(flag == 0) {
            try {
                focusMapper.insertSelective(focus);
                return "关注成功";
            } catch (Exception e) {
                return "关注失败";
            }
        }
        return "已关注";
    }
}
