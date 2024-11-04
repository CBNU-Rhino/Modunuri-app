package com.example.modunuri;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

import java.util.Map;

public interface UserApiService {

    // 회원가입 엔드포인트
    @POST("users/signup")
    Call<Void> signup(@Body UserDTO userDTO);

    // 로그인 체크 엔드포인트
    @GET("users/checkLogin")
    Call<Boolean> checkLoginStatus();

    // 관심 콘텐츠 추가 엔드포인트
    @POST("users/addFavorite")
    Call<Void> addFavorite(@Query("contentId") String contentId, @Query("contentTypeId") String contentTypeId);

    // 관심 콘텐츠 삭제 엔드포인트
    @POST("users/removeFavorite")
    Call<Void> removeFavorite(@Query("contentId") String contentId);

    // 관심 콘텐츠 가져오기 엔드포인트
    @GET("users/getFavoriteContents")
    Call<Map<String, String>> getFavoriteContents();

    // 관심 콘텐츠 저장 엔드포인트
    @POST("users/save")
    Call<String> saveFavoriteContent(@Body Map<String, String> requestBody);

    // 즐겨찾기 확인 엔드포인트
    @POST("users/checkSaved")
    Call<Boolean> checkSavedContent(@Body Map<String, String> requestBody);
}
