package com.example.modunuri.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modunuri.ApiClient;
import com.example.modunuri.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText useridField, passwordField;
    private Button loginButton;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        useridField = findViewById(R.id.user_id);
        passwordField = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);

        userService = ApiClient.getClient("http://10.0.2.2:8080").create(UserService.class);

        loginButton.setOnClickListener(v -> {
            String userId = useridField.getText().toString();
            String password = passwordField.getText().toString();

            // 로그인 API 호출
            Call<Void> loginCall = userService.loginUser(userId, password);
            loginCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {  // 로그인 API 호출 성공 시
                        // 로그인 후 상태 확인을 위해 checkLoginStatus 호출
                        Call<Boolean> checkLoginCall = userService.checkLoginStatus();
                        checkLoginCall.enqueue(new Callback<Boolean>() {
                            @Override
                            public void onResponse(Call<Boolean> call, Response<Boolean> checkResponse) {
                                if (checkResponse.isSuccessful() && checkResponse.body() != null && checkResponse.body()) {
                                    // 실제로 로그인 상태인 경우
                                    Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // 로그인 상태가 아닌 경우 (예: 비정상적 로그인 성공)
                                    Toast.makeText(LoginActivity.this, "로그인 실패: 잘못된 사용자 정보입니다.", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<Boolean> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, "로그인 상태 확인 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        // 로그인 실패 처리
                        Toast.makeText(LoginActivity.this, "로그인 실패: 서버 오류", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });


    }
}