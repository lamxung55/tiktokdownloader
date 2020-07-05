package com.mina.tiktokdownloader.ui.downloaded;

public class DownloadedVideoModel {
    private String videoName;
    private String videoPath;
    private String videoTag;
    private int videoType; //0: video; 1: music
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoPath() {
        return videoPath;
    }

    public void setVideoPath(String videoPath) {
        this.videoPath = videoPath;
    }

    public String getVideoTag() {
        return videoTag;
    }

    public void setVideoTag(String videoTag) {
        this.videoTag = videoTag;
    }

    public int getVideoType() {
        return videoType;
    }

    public void setVideoType(int videoType) {
        this.videoType = videoType;
    }
}
