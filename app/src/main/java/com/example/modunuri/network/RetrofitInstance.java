package com.example.modunuri.network;

import com.example.modunuri.UserApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    private static final String BASE_URL = "http://10.0.2.2:8080/"; // 로컬 서버 URL
    private static Retrofit retrofit;

    // Retrofit 인스턴스를 제공하는 메서드
    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    // UserApiService 인터페이스 생성 메서드
    public static UserApiService getApiService() {
        return getRetrofitInstance().create(UserApiService.class);
    }
}
