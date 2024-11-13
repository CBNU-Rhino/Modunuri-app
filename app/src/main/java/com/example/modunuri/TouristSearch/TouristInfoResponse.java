package com.example.modunuri.TouristSearch;

import java.util.List;

public class TouristInfoResponse {
    private List<TouristInfoDTO> items;

    public List<TouristInfoDTO> getItems() {
        return items;
    }

    public void setItems(List<TouristInfoDTO> items) {
        this.items = items;
    }
}
