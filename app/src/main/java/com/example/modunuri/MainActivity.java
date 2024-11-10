package com.example.modunuri;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.modunuri.user.LoginActivity;
import com.example.modunuri.user.SignupActivity;

public class MainActivity extends AppCompatActivity {

    private TextView welcomeText;
    private Button loginButton, signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        welcomeText = findViewById(R.id.welcome_text);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);

        // 로그인 상태에 따라 화면을 변경
        checkLoginStatus();

        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SignupActivity.class);
            startActivity(intent);
        });
    }

    private void checkLoginStatus() {
        boolean isLoggedIn = false; // 실제 로그인 상태 확인 로직 추가 필요

        if (isLoggedIn) {
            welcomeText.setText("안녕하세요, 사용자님!");
            loginButton.setVisibility(View.GONE);
            signupButton.setVisibility(View.GONE);
        } else {
            welcomeText.setText("로그인이 필요합니다.");
            loginButton.setVisibility(View.VISIBLE);
            signupButton.setVisibility(View.VISIBLE);
        }
    }
}