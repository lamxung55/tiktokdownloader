package com.mina.tiktokdownloader.apiresponse;

public class Apiresponse {
    private String link_download_original;
    private String link_download_without_watermark;
    private String link_download_music;
    private String author_nickname;
    private String music_title;
    private String music_cover;
    private String id;

    public String getAuthor_nickname() {
        return author_nickname;
    }

    public void setAuthor_nickname(String author_nickname) {
        this.author_nickname = author_nickname;
    }

    public String getLink_download_original() {
        return link_download_original;
    }

    public void setLink_download_original(String link_download_original) {
        this.link_download_original = link_download_original;
    }

    public String getLink_download_without_watermark() {
        return link_download_without_watermark;
    }

    public void setLink_download_without_watermark(String link_download_without_watermark) {
        this.link_download_without_watermark = link_download_without_watermark;
    }

    public String getLink_download_music() {
        return link_download_music;
    }

    public void setLink_download_music(String link_download_music) {
        this.link_download_music = link_download_music;
    }

    public String getMusic_title() {
        return music_title;
    }

    public void setMusic_title(String music_title) {
        this.music_title = music_title;
    }

    public String getMusic_cover() {
        return music_cover;
    }

    public void setMusic_cover(String music_cover) {
        this.music_cover = music_cover;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
