package com.hu.video.entity.dto;

public class ResultDTO<T> {
    private Boolean result;

    private String message;

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultDTO() {
    }

    public ResultDTO(Boolean result, String message) {
        this.result = result;
        this.message = message;
    }
}
