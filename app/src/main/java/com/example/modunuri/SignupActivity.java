package com.example.modunuri;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.modunuri.network.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {

    private EditText usernameEditText, userIdEditText, passwordEditText;
    private Button signupButton;
    private UserApiService userApiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        usernameEditText = findViewById(R.id.usernameEditText);
        userIdEditText = findViewById(R.id.userIdEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        signupButton = findViewById(R.id.signupButton);

        userApiService = RetrofitInstance.getRetrofitInstance().create(UserApiService.class);

        signupButton.setOnClickListener(v -> {
            String username = usernameEditText.getText().toString();
            String userId = userIdEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            UserDTO userDTO = new UserDTO(username, userId, password);
            signup(userDTO);
        });
    }

    private void signup(UserDTO userDTO) {
        Call<Void> call = userApiService.signup(userDTO);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(SignupActivity.this, "회원가입 성공!", Toast.LENGTH_SHORT).show();
                    finish();  // 회원가입 완료 후 액티비티 종료
                } else {
                    Toast.makeText(SignupActivity.this, "회원가입 실패!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(SignupActivity.this, "서버 연결 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
