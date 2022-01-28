package com.hu.video.entity;

public class TVideotype {
    private Long videotypeId;

    private String typeName;

    public Long getVideotypeId() {
        return videotypeId;
    }

    public void setVideotypeId(Long videotypeId) {
        this.videotypeId = videotypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    @Override
    public String toString() {
        return "TVideotype{" +
                "videotypeId=" + videotypeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
