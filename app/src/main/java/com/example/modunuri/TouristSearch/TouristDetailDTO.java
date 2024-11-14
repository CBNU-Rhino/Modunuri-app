package com.example.modunuri.TouristSearch;

import com.google.gson.annotations.SerializedName;

public class TouristDetailDTO {
    @SerializedName("contentid")
    private String contentId;

    @SerializedName("contenttypeid")
    private String contentTypeId;

    @SerializedName("title")
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("addr1")
    private String addr1;

    @SerializedName("addr2")
    private String addr2;

    @SerializedName("firstimage")
    private String firstImage;

    @SerializedName("mapx")
    private String mapX; // 추가된 필드

    @SerializedName("mapy")
    private String mapY; // 추가된 필드

    // Getter and Setter methods for each field
    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(String contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getFirstImage() {
        return firstImage;
    }

    public void setFirstImage(String firstImage) {
        this.firstImage = firstImage;
    }

    public String getMapX() { // 추가된 메서드
        return mapX;
    }

    public void setMapX(String mapX) {
        this.mapX = mapX;
    }

    public String getMapY() { // 추가된 메서드
        return mapY;
    }

    public void setMapY(String mapY) {
        this.mapY = mapY;
    }
}
