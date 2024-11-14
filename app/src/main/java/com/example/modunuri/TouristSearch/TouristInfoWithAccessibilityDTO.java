package com.example.modunuri.TouristSearch;

import com.google.gson.annotations.SerializedName;

public class TouristInfoWithAccessibilityDTO {

    @SerializedName("touristDetail")
    private TouristDetailDTO touristDetail;

    @SerializedName("accessibilityInfo")
    private AccessibilityInfoDTO accessibilityInfo;

    // Getters
    public TouristDetailDTO getTouristDetail() {
        return touristDetail;
    }

    public AccessibilityInfoDTO getAccessibilityInfo() {
        return accessibilityInfo;
    }
}
