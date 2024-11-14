package com.example.modunuri.TouristSearch;

import com.google.gson.annotations.SerializedName;

public class AccessibilityInfoDTO {

    @SerializedName("parking")
    private String parking;

    @SerializedName("elevator")
    private String elevator;

    @SerializedName("restroom")
    private String restroom;

    @SerializedName("braileblock")
    private String braileBlock;

    // Getters
    public String getParking() {
        return parking;
    }

    public String getElevator() {
        return elevator;
    }

    public String getRestroom() {
        return restroom;
    }

    public String getBraileblock() {
        return braileBlock;
    }
}
