package com.example.modunuri.user;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface UserService {
    @POST("/users/signup")
    Call<Void> registerUser(@Body UserDTO userDTO);

    @POST("/users/login")
    Call<Void> loginUser(@Body UserDTO userDTO);

    @POST("/users/logout")
    Call<Void> logout();

    @GET("/users/checkLogin")
    Call<Boolean> checkLoginStatus();
}
