package com.example.modunuri.TouristSearch;

import com.google.gson.annotations.SerializedName;

public class TouristInfoResponse {
    @SerializedName("touristInfo")
    private TouristInfoWithAccessibilityDTO touristInfo;

    @SerializedName("username")
    private String username;

    public TouristInfoWithAccessibilityDTO getTouristInfo() {
        return touristInfo;
    }

    public void setTouristInfo(TouristInfoWithAccessibilityDTO touristInfo) {
        this.touristInfo = touristInfo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
