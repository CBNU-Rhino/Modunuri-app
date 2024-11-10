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

            Call<Void> call = userService.loginUser(userId, password);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(LoginActivity.this, "로그인 성공!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "로그인 실패!", Toast.LENGTH_SHORT).show();
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
