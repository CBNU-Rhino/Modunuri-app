package com.example.modunuri.TouristSearch;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.example.modunuri.R;
import com.example.modunuri.network.ApiClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristDetailActivity extends AppCompatActivity {

    private ImageView touristImageView;
    private TextView titleTextView, overviewTextView, addressTextView, parkingTextView, elevatorTextView, restroomTextView, braileBlockTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_detail);

        // View 초기화
        touristImageView = findViewById(R.id.touristImageView);
        titleTextView = findViewById(R.id.titleTextView);
        overviewTextView = findViewById(R.id.overviewTextView);
        addressTextView = findViewById(R.id.addressTextView);
        parkingTextView = findViewById(R.id.parkingTextView);
        elevatorTextView = findViewById(R.id.elevatorTextView);
        restroomTextView = findViewById(R.id.restroomTextView);
        braileBlockTextView = findViewById(R.id.braileBlockTextView);

        // Intent로 전달된 데이터 가져오기
        String contentId = getIntent().getStringExtra("contentId");
        String contentTypeId = getIntent().getStringExtra("contentTypeId");

        // 관광지 정보 로드
        loadTouristDetail(contentId, contentTypeId);
    }

    private void loadTouristDetail(String contentId, String contentTypeId) {
        TouristService touristService = ApiClient.getClient("http://10.0.2.2:8080").create(TouristService.class);
        Call<TouristInfoResponse> call = touristService.getTouristInfo(contentId, contentTypeId);

        call.enqueue(new Callback<TouristInfoResponse>() {
            @Override
            public void onResponse(Call<TouristInfoResponse> call, Response<TouristInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    TouristInfoWithAccessibilityDTO touristInfo = response.body().getTouristInfo();

                    // 데이터 설정
                    titleTextView.setText(touristInfo.getTouristDetail().getTitle());
                    overviewTextView.setText(touristInfo.getTouristDetail().getOverview());
                    addressTextView.setText(touristInfo.getTouristDetail().getAddr1() + " " + touristInfo.getTouristDetail().getAddr2());

                    // 이미지 설정
                    String imageUrl = touristInfo.getTouristDetail().getFirstImage();
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(TouristDetailActivity.this)
                                .load(imageUrl)
                                .placeholder(R.drawable.placeholder_image)
                                .into(touristImageView);
                    }

                    // 접근성 정보 설정
                    parkingTextView.setText("장애인 주차구역: " + getOrDefault(touristInfo.getAccessibilityInfo().getParking()));
                    elevatorTextView.setText("엘리베이터: " + getOrDefault(touristInfo.getAccessibilityInfo().getElevator()));
                    restroomTextView.setText("장애인 화장실: " + getOrDefault(touristInfo.getAccessibilityInfo().getRestroom()));
                    braileBlockTextView.setText("점자블록: " + getOrDefault(touristInfo.getAccessibilityInfo().getBraileblock()));
                }
            }

            @Override
            public void onFailure(Call<TouristInfoResponse> call, Throwable t) {
                Toast.makeText(TouristDetailActivity.this, "관광지 상세 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
            }
            // 비어있는 정보가 있으면 "정보 없음"으로 표시하는 헬퍼 메서드
            private String getOrDefault(String value) {
                return (value == null || value.isEmpty()) ? "정보 없음" : value;
            }
        });
    }
}
