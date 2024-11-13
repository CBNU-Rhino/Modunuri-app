package com.example.modunuri.TouristSearch;
// TouristService.java
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

public interface TouristService {
    @GET("/touristSpot/Json/api/tourist-info")
    Call<List<TouristInfoDTO>> getTouristInfo(
            @Query("region") String region,
            @Query("sigungu") String sigungu,
            @Query("contentTypeId") int contentTypeId
    );

}
