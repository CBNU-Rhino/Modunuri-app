package com.example.modunuri.TouristSearch;

// TouristInfoDTO.java
import com.google.gson.annotations.SerializedName;

public class TouristInfoDTO {
    @SerializedName("addr1")
    private String addr1;

    @SerializedName("addr2")
    private String addr2;

    @SerializedName("areacode")
    private String areaCode;

    @SerializedName("contentid")
    private String contentId;

    @SerializedName("contenttypeid")
    private String contentTypeId;

    @SerializedName("firstimage")
    private String firstImage;

    @SerializedName("firstimage2")
    private String firstImage2;

    @SerializedName("mapx")
    private String mapX;

    @SerializedName("mapy")
    private String mapY;

    @SerializedName("title")
    private String title;

    @SerializedName("zipcode")
    private String zipCode;

    public String getTitle() {
        return title;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getFirstImage() {
        return firstImage;
    }
}
