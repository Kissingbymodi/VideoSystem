package com.hu.video.service;

import java.util.List;

public interface IFocusService {
    String focusVerify(Long userId, Long focusedIdLong);

    String addFocused(Long userId, Long focusedIdLong);

    List<Long> getUserFocusList(Long userId);
}
