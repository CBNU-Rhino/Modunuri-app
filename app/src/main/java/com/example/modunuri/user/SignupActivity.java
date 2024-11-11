package com.example.modunuri.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modunuri.home.HomeActivity;
import com.example.modunuri.network.ApiClient;
import com.example.modunuri.R;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameField, passwordField,userIdField;
    private Button signupButton;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameField = findViewById(R.id.username);
        userIdField = findViewById(R.id.user_id);
        passwordField = findViewById(R.id.password);
        signupButton = findViewById(R.id.signup_button);

        userService = ApiClient.getClient("http://10.0.2.2:8080").create(UserService.class);

        signupButton.setOnClickListener(v -> {
            UserDTO user = new UserDTO();
            user.setUsername(usernameField.getText().toString());
            user.setUserId(userIdField.getText().toString());
            user.setPassword(passwordField.getText().toString());

            Call<ResponseBody> call = userService.registerUser(user);
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        try {
                            // 서버의 JSON 응답을 문자열로 변환하여 메시지 추출
                            String message = response.body().string();
                            Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                            startActivity(intent);
                            finish();
                        } catch (IOException e) {
                            e.printStackTrace();
                            Toast.makeText(SignupActivity.this, "응답 처리 오류", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        try {
                            // 서버에서 에러 메시지 반환 시 해당 메시지를 출력
                            String errorMessage = response.errorBody().string();
                            Toast.makeText(SignupActivity.this, "회원가입 실패: " + errorMessage, Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            Toast.makeText(SignupActivity.this, "회원가입 실패: 서버 오류", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(SignupActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}