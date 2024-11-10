package com.example.modunuri.user;

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

            Call<Void> call = userService.registerUser(user);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(SignupActivity.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                        // 로그인 화면이나 홈 화면으로 이동
                    } else {
                        Toast.makeText(SignupActivity.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SignupActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
