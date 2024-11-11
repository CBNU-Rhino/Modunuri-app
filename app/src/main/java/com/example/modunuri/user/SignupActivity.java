package com.example.modunuri.user;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.modunuri.network.ApiClient;
import com.example.modunuri.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameField, passwordField;
    private Button signupButton;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameField = findViewById(R.id.username);
        passwordField = findViewById(R.id.password);
        signupButton = findViewById(R.id.signup_button);

        userService = ApiClient.getClient("http://10.0.2.2:8080").create(UserService.class);

        signupButton.setOnClickListener(v -> {
            UserDTO user = new UserDTO();
            user.setUsername(usernameField.getText().toString());
            user.setPassword(passwordField.getText().toString());

            Call<String> call = userService.registerUser(user); // 타입 일치 확인

            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        Toast.makeText(SignupActivity.this, response.body(), Toast.LENGTH_SHORT).show(); // 서버 메시지 출력
                        Intent intent = new Intent(SignupActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(SignupActivity.this, "회원가입 실패: 서버 오류", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(SignupActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });
    }
}
