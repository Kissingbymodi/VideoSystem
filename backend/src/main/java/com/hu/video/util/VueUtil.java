package com.hu.video.util;

public class VueUtil {
/*    data: T;
    status: number;
    statusText: string;*/
    Object data;
    String message;

    Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static VueUtil success(String statusText, Object data) {
        VueUtil response = new VueUtil();
        response.setCode(20000);
        response.setMessage(statusText);
        response.setData(data);
        return response;
    }

    public static VueUtil fail(String statusText) {
        VueUtil response = new VueUtil();
        response.setCode(400);
        response.setMessage(statusText);
        response.setData(null);
        return response;
    }
}
