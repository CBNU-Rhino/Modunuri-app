package com.example.modunuri.TouristSearch;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.modunuri.R;
import com.example.modunuri.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristDetailActivity extends AppCompatActivity {

    private TextView titleTextView, overviewTextView, addressTextView, accessibilityTextView;
    private TouristService touristService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_detail);

        titleTextView = findViewById(R.id.titleTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        addressTextView = findViewById(R.id.addressTextView);
        accessibilityTextView = findViewById(R.id.accessibilityTextView);

        // TouristService 초기화
        touristService = ApiClient.getClient("http://10.0.2.2:8080").create(TouristService.class);

        // Intent로부터 contentId와 contentTypeId 가져오기
        String contentId = getIntent().getStringExtra("contentId");
        String contentTypeId = getIntent().getStringExtra("contentTypeId");

        // API 호출
        getTouristDetail(contentId, contentTypeId);
    }

    private void getTouristDetail(String contentId, String contentTypeId) {
        Call<TouristInfoResponse> call = touristService.getTouristInformation(contentId, contentTypeId);
        call.enqueue(new Callback<TouristInfoResponse>() {
            @Override
            public void onResponse(Call<TouristInfoResponse> call, Response<TouristInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TouristInfoWithAccessibilityDTO touristInfo = response.body().getTouristInfo();

                    // 데이터를 TextView에 설정
                    titleTextView.setText(touristInfo.getTouristDetail().getTitle());
                    overviewTextView.setText(touristInfo.getTouristDetail().getOverview());
                    addressTextView.setText(touristInfo.getTouristDetail().getAddr1());
                    accessibilityTextView.setText(touristInfo.getAccessibilityInfo().getWheelchair());
                } else {
                    Toast.makeText(TouristDetailActivity.this, "데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TouristInfoResponse> call, Throwable t) {
                Toast.makeText(TouristDetailActivity.this, "오류 발생: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
