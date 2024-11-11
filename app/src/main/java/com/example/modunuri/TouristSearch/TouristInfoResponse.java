package com.example.modunuri.TouristSearch;

import com.example.modunuri.TouristSearch.TouristInfoDTO;

import java.util.List;

public class TouristInfoResponse {
    private ResponseData response;

    public List<TouristInfoDTO> getItems() {
        if (response != null && response.body != null && response.body.items != null) {
            return response.body.items.item;
        }
        return null;
    }

    // 내부 클래스 정의
    public static class ResponseData {
        private BodyData body;
    }

    public static class BodyData {
        private ItemsData items;
    }

    public static class ItemsData {
        private List<TouristInfoDTO> item;
    }
}
