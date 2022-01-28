package com.hu.video.util;

import com.hu.video.entity.dto.ResultDTO;

/**
 * ResultUtil 工具类可以快速的获取一个ResultDTO
 */
public class ResultUtil {

    public static ResultDTO successResult(String msg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(true);
        resultDTO.setMessage(msg);
        return resultDTO;
    }

    public static ResultDTO failResult(String msg) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setResult(false);
        resultDTO.setMessage(msg);
        return resultDTO;
    }

    public static <T>ResultDTO<T> successResult(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(true);
        resultDTO.setMessage("请求成功");
        resultDTO.setData(t);
        return resultDTO;
    }

    public static <T>ResultDTO<T> failResult(T t) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(false);
        resultDTO.setMessage("请求失败");
        resultDTO.setData(t);
        return resultDTO;
    }

    public static <T>ResultDTO<T> successResult(T t, String msg) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(true);
        resultDTO.setMessage(msg);
        resultDTO.setData(t);
        return resultDTO;
    }

    public static <T>ResultDTO<T> failResult(T t, String msg) {
        ResultDTO<T> resultDTO = new ResultDTO<T>();
        resultDTO.setResult(false);
        resultDTO.setMessage(msg);
        resultDTO.setData(t);
        return resultDTO;
    }
}
