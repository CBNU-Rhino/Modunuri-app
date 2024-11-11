package com.example.modunuri.home;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.modunuri.network.ApiClient;
import com.example.modunuri.R;
import com.example.modunuri.user.LoginActivity;
import com.example.modunuri.user.SignupActivity;
import com.example.modunuri.user.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    private TextView greetingText;
    private Button loginButton, signupButton, logoutButton;
    private View guestLayout, userLayout;
    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 레이아웃 요소 초기화
        guestLayout = findViewById(R.id.guest_layout);
        userLayout = findViewById(R.id.user_layout);
        greetingText = findViewById(R.id.greeting_text);
        loginButton = findViewById(R.id.login_button);
        signupButton = findViewById(R.id.signup_button);
        logoutButton = findViewById(R.id.logout_button);

        userService = ApiClient.getClient("http://10.0.2.2:8080").create(UserService.class);

        // 로그인 상태 확인
        checkLoginStatus();

        // 버튼 클릭 리스너 설정
        loginButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        signupButton.setOnClickListener(v -> {
            Intent intent = new Intent(HomeActivity.this, SignupActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            // 로그아웃 처리 후 UI 업데이트
            logout();
            guestLayout.setVisibility(View.VISIBLE);
            userLayout.setVisibility(View.GONE);
        });
    }

    private void checkLoginStatus() {
        Call<Boolean> call = userService.checkLoginStatus();
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    boolean isLoggedIn = response.body();
                    if (isLoggedIn) {
                        // 로그인된 경우 UI 업데이트
                        guestLayout.setVisibility(View.GONE);
                        userLayout.setVisibility(View.VISIBLE);
                        greetingText.setText("안녕하세요");

                        Log.d(TAG, "User is logged in.");  // 로그인 성공 로그
                    } else {
                        // 로그인되지 않은 경우 UI 업데이트
                        guestLayout.setVisibility(View.VISIBLE);
                        userLayout.setVisibility(View.GONE);

                        Log.d(TAG, "User is not logged in.");  // 로그인되지 않은 상태 로그
                    }
                } else {
                    Toast.makeText(HomeActivity.this, "로그인 상태 확인 실패", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Failed to check login status: " + response.message());  // 실패 시 로그
                }
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e(TAG, "Error checking login status: " + t.getMessage());  // 에러 로그
            }
        });
    }

    private void logout() {
        Call<Void> call = userService.logout();
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(HomeActivity.this, "로그아웃되었습니다.", Toast.LENGTH_SHORT).show();
                    // 로그아웃 후 UI 업데이트
                    guestLayout.setVisibility(View.VISIBLE);
                    userLayout.setVisibility(View.GONE);
                } else {
                    Toast.makeText(HomeActivity.this, "로그아웃 실패: 서버 오류", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(HomeActivity.this, "로그아웃 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
