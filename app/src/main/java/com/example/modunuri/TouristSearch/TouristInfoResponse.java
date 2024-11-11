package com.example.modunuri.TouristSearch;

// TouristInfoResponse.java
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class TouristInfoResponse {
    @SerializedName("response")
    private ResponseData response;

    public ResponseData getResponse() {
        return response;
    }

    public class ResponseData {
        @SerializedName("body")
        private BodyData body;

        public BodyData getBody() {
            return body;
        }

        public class BodyData {
            @SerializedName("items")
            private ItemsData items;

            public ItemsData getItems() {
                return items;
            }

            public class ItemsData {
                @SerializedName("item")
                private List<TouristInfoDTO> item;

                public List<TouristInfoDTO> getItem() {
                    return item;
                }
            }
        }
    }
}

