package com.example.modunuri.user;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {

    @POST("/users/signup")
    Call<ResponseBody> registerUser(@Body UserDTO userDTO);

    @FormUrlEncoded
    @POST("/users/login")
    Call<Void> loginUser(@Field("userId") String userId, @Field("password") String password);

    @POST("/users/logout")
    Call<Void> logout();

    @GET("/users/checkLogin")
    Call<Boolean> checkLoginStatus();
}
