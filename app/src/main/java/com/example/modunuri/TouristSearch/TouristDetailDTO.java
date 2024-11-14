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

    // Getters
    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public String getFirstImage() {
        return firstImage;
    }
}
