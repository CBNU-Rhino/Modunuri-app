package com.example.modunuri.TouristSearch;

import com.google.gson.annotations.SerializedName;

public class AccessibilityInfoDTO {
    @SerializedName("contentid")
    private String contentId;

    @SerializedName("parking")
    private String parking;

    @SerializedName("publictransport")
    private String publicTransport;

    @SerializedName("wheelchair")
    private String wheelchair;

    @SerializedName("exit")
    private String exit;

    @SerializedName("elevator")
    private String elevator;

    @SerializedName("restroom")
    private String restroom;

    @SerializedName("braileblock")
    private String braileBlock;

    @SerializedName("stroller")
    private String stroller;

    @SerializedName("lactationroom")
    private String lactationRoom;

    public String getContentId() {
        return contentId;
    }

    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    public String getParking() {
        return parking;
    }

    public void setParking(String parking) {
        this.parking = parking;
    }

    public String getPublicTransport() {
        return publicTransport;
    }

    public void setPublicTransport(String publicTransport) {
        this.publicTransport = publicTransport;
    }

    public String getWheelchair() {
        return wheelchair;
    }

    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    public String getExit() {
        return exit;
    }

    public void setExit(String exit) {
        this.exit = exit;
    }

    public String getElevator() {
        return elevator;
    }

    public void setElevator(String elevator) {
        this.elevator = elevator;
    }

    public String getRestroom() {
        return restroom;
    }

    public void setRestroom(String restroom) {
        this.restroom = restroom;
    }

    public String getBraileBlock() {
        return braileBlock;
    }

    public void setBraileBlock(String braileBlock) {
        this.braileBlock = braileBlock;
    }

    public String getStroller() {
        return stroller;
    }

    public void setStroller(String stroller) {
        this.stroller = stroller;
    }

    public String getLactationRoom() {
        return lactationRoom;
    }

    public void setLactationRoom(String lactationRoom) {
        this.lactationRoom = lactationRoom;
    }
}
