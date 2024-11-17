package com.example.modunuri.TouristSearch;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.modunuri.BuildConfig;
import com.example.modunuri.R;
import com.example.modunuri.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristDetailActivity extends AppCompatActivity {

    private ImageView touristImageView;
    private TextView titleTextView, overviewTextView, addressTextView, parkingTextView, elevatorTextView, restroomTextView, braileBlockTextView;
    private WebView mapWebView;

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
        mapWebView = findViewById(R.id.mapWebView);

        // WebView 설정
        WebSettings webSettings = mapWebView.getSettings();
        webSettings.setDomStorageEnabled(true); // DOM Storage 허용
        webSettings.setJavaScriptEnabled(true); // JavaScript 사용 가능하도록 설정
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        mapWebView.setWebViewClient(new WebViewClient()); // WebView에서 링크 클릭 시 새 창이 열리지 않도록 설정

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
                    titleTextView.setText(getOrDefault(touristInfo.getTouristDetail().getTitle()));
                    overviewTextView.setText(getOrDefault(touristInfo.getTouristDetail().getOverview()));
                    addressTextView.setText(getOrDefault(touristInfo.getTouristDetail().getAddr1()) + " " +
                            getOrDefault(touristInfo.getTouristDetail().getAddr2()));

                    // 이미지 설정
                    String imageUrl = touristInfo.getTouristDetail().getFirstImage();
                    if (imageUrl != null && !imageUrl.isEmpty()) {
                        Glide.with(TouristDetailActivity.this)
                                .load(imageUrl)
                                .placeholder(R.drawable.placeholder_image)
                                .into(touristImageView);
                    } else {
                        touristImageView.setImageResource(R.drawable.placeholder_image);
                    }

                    // 접근성 정보 설정
                    parkingTextView.setText("장애인 주차구역: " + getOrDefault(touristInfo.getAccessibilityInfo().getParking()));
                    elevatorTextView.setText("엘리베이터: " + getOrDefault(touristInfo.getAccessibilityInfo().getElevator()));
                    restroomTextView.setText("장애인 화장실: " + getOrDefault(touristInfo.getAccessibilityInfo().getRestroom()));
                    braileBlockTextView.setText("점자블록: " + getOrDefault(touristInfo.getAccessibilityInfo().getBraileblock()));

                    // 지도 로드
                    try {
                        double mapX = Double.parseDouble(touristInfo.getTouristDetail().getMapX());
                        double mapY = Double.parseDouble(touristInfo.getTouristDetail().getMapY());
                        Log.d("TouristDetailActivity", "mapX: " + mapX + ", mapY: " + mapY);
                        loadMap(mapX, mapY);
                    } catch (NumberFormatException e) {
                        Toast.makeText(TouristDetailActivity.this, "유효한 지도 위치가 없습니다.", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(TouristDetailActivity.this, "관광지 정보를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TouristInfoResponse> call, Throwable t) {
                Toast.makeText(TouristDetailActivity.this, "서버와의 연결에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadMap(double mapX, double mapY) {
        String apiKey = BuildConfig.KAKAO_MAP_KEY; // BuildConfig에서 API 키 불러오기
        String html = "<!DOCTYPE html>" +
                "<html>" +
                "<head>" +
                "    <meta charset='utf-8'>" +
                "    <meta name='viewport' content='width=device-width, initial-scale=1.0'>" +
                "    <script type='text/javascript' src='https://dapi.kakao.com/v2/maps/sdk.js?appkey=" + apiKey + "&libraries=services'></script>" +
                "    <style>html, body { margin: 0; padding: 0; height: 100%; }</style>" +
                "</head>" +
                "<body>" +
                "    <div id='map' style='width:100%;height:100%;'></div>" +
                "    <script>" +
                "        var mapContainer = document.getElementById('map');" +
                "        var mapOption = {" +
                "            center: new kakao.maps.LatLng(" + mapY + ", " + mapX + ")," +
                "            level: 3" +
                "        };" +
                "        var map = new kakao.maps.Map(mapContainer, mapOption);" +
                "        var markerPosition = new kakao.maps.LatLng(" + mapY + ", " + mapX + ");" +
                "        var marker = new kakao.maps.Marker({" +
                "            position: markerPosition" +
                "        });" +
                "        marker.setMap(map);" +
                "        var customOverlay = new kakao.maps.CustomOverlay({" +
                "            position: markerPosition," +
                "            content: '<div style=\"padding:5px;background-color:white;border:1px solid black;border-radius:5px;\">관광지</div>'," +
                "            yAnchor: 1.5" +
                "        });" +
                "        customOverlay.setMap(map);" +
                "    </script>" +
                "</body>" +
                "</html>";

        mapWebView.loadDataWithBaseURL(null, html, "text/html", "UTF-8", null);
    }



    private String getOrDefault(String value) {
        return (value == null || value.isEmpty()) ? "정보 없음" : value;
    }
}
