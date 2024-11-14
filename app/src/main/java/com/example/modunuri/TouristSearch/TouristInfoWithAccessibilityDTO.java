package com.example.modunuri.TouristSearch;

import com.google.gson.annotations.SerializedName;

public class TouristInfoWithAccessibilityDTO {
    @SerializedName("touristDetail")
    private TouristDetailDTO touristDetail;

    @SerializedName("accessibilityInfo")
    private AccessibilityInfoDTO accessibilityInfo;

    // Getters and Setters
    public TouristDetailDTO getTouristDetail() {
        return touristDetail;
    }

    public void setTouristDetail(TouristDetailDTO touristDetail) {
        this.touristDetail = touristDetail;
    }

    public AccessibilityInfoDTO getAccessibilityInfo() {
        return accessibilityInfo;
    }

    public void setAccessibilityInfo(AccessibilityInfoDTO accessibilityInfo) {
        this.accessibilityInfo = accessibilityInfo;
    }
}
