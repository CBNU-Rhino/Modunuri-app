package com.example.modunuri.TouristSearch;// TouristSearchActivity.java
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.modunuri.R;
import com.example.modunuri.network.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class TouristSearchActivity extends AppCompatActivity {

    private EditText regionInput, sigunguInput, contentTypeIdInput;
    private Button searchButton;
    private RecyclerView touristRecyclerView;
    private TouristAdapter touristAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_search);

        regionInput = findViewById(R.id.region_input);
        sigunguInput = findViewById(R.id.sigungu_input);
        contentTypeIdInput = findViewById(R.id.content_type_id_input);
        searchButton = findViewById(R.id.search_button);
        touristRecyclerView = findViewById(R.id.tourist_recycler_view);

        touristRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        touristAdapter = new TouristAdapter();
        touristRecyclerView.setAdapter(touristAdapter);

        searchButton.setOnClickListener(v -> searchTouristSpots());
    }

    private void searchTouristSpots() {
        String region = regionInput.getText().toString();
        String sigungu = sigunguInput.getText().toString();
        int contentTypeId;

        try {
            contentTypeId = Integer.parseInt(contentTypeIdInput.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(this, "유효한 관광 타입 ID를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        TouristService touristService = ApiClient.getClient("http://10.0.2.2:8080").create(TouristService.class);
        Call<TouristInfoResponse> call = touristService.getTouristInfo(region, sigungu, contentTypeId);
        call.enqueue(new Callback<TouristInfoResponse>() {
            @Override
            public void onResponse(Call<TouristInfoResponse> call, Response<TouristInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TouristInfoDTO> touristInfoList = response.body().getResponse().getBody().getItems().getItem();
                    touristAdapter.setTouristInfoList(touristInfoList);
                } else {
                    Toast.makeText(TouristSearchActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TouristInfoResponse> call, Throwable t) {
                Toast.makeText(TouristSearchActivity.this, "에러: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
