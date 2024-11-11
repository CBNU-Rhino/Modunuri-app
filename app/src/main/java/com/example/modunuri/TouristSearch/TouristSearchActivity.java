package com.example.modunuri.TouristSearch;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.modunuri.R;
import com.example.modunuri.network.ApiClient;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TouristSearchActivity extends AppCompatActivity {

    private Spinner regionSpinner, sigunguSpinner;
    private EditText contentTypeIdInput;
    private Button searchButton;
    private RecyclerView touristRecyclerView;
    private TouristAdapter touristAdapter;
    private Map<String, String[]> regionData;
    private TouristService touristService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist_search);

        // 초기화
        regionSpinner = findViewById(R.id.region_spinner);
        sigunguSpinner = findViewById(R.id.sigungu_spinner);
        contentTypeIdInput = findViewById(R.id.content_type_id_input);
        searchButton = findViewById(R.id.search_button);
        touristRecyclerView = findViewById(R.id.tourist_recycler_view);

        // RecyclerView 설정
        touristRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        touristAdapter = new TouristAdapter();
        touristRecyclerView.setAdapter(touristAdapter);

        // Retrofit 설정 및 TouristService 초기화
        touristService = ApiClient.getClient("http://10.0.2.2:8080").create(TouristService.class);

        // 지역 데이터 정의
        initializeRegionData();

        // 지역 Spinner에 데이터 설정
        ArrayAdapter<String> regionAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, regionData.keySet().toArray(new String[0]));
        regionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regionSpinner.setAdapter(regionAdapter);

        // 지역 선택 시 시/군/구 Spinner 업데이트
        regionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedRegion = regionSpinner.getSelectedItem().toString();
                updateSigunguSpinner(selectedRegion);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        // 검색 버튼 클릭 시 로직 설정
        searchButton.setOnClickListener(v -> {
            String region = regionSpinner.getSelectedItem().toString();
            String sigungu = sigunguSpinner.getSelectedItem().toString();
            String contentTypeIdStr = contentTypeIdInput.getText().toString();

            try {
                int contentTypeId = Integer.parseInt(contentTypeIdStr); // 문자열을 int로 변환
                searchTouristInfo(region, sigungu, contentTypeId); // int형으로 전달
            } catch (NumberFormatException e) {
                Toast.makeText(this, "관광지 타입 ID를 올바르게 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initializeRegionData() {
        // 지역 데이터 초기화
        regionData = new HashMap<>();
        regionData.put("서울특별시", new String[]{"종로구", "중구", "용산구", "성동구", "광진구", "동대문구", "중랑구", "성북구", "강북구", "도봉구", "노원구", "은평구", "서대문구", "마포구", "양천구", "강서구", "구로구", "금천구", "영등포구", "동작구", "관악구", "서초구", "강남구", "송파구", "강동구"});
        regionData.put("부산광역시", new String[]{"중구", "서구", "동구", "영도구", "부산진구", "동래구", "남구", "북구", "해운대구", "사하구", "금정구", "강서구", "연제구", "수영구", "사상구", "기장군"});
        regionData.put("대구광역시", new String[]{"중구", "동구", "서구", "남구", "북구", "수성구", "달서구", "달성군", "군위군"});
        regionData.put("인천광역시", new String[]{"중구", "동구", "미추홀구", "연수구", "남동구", "부평구", "계양구", "서구", "강화군", "옹진군"});
        regionData.put("광주광역시", new String[]{"동구", "서구", "남구", "북구", "광산구"});
        regionData.put("대전광역시", new String[]{"동구", "중구", "서구", "유성구", "대덕구"});
        regionData.put("울산광역시", new String[]{"중구", "남구", "동구", "북구", "울주군"});
        regionData.put("세종특별자치시", new String[]{"세종특별자치시"});
        regionData.put("경기도", new String[]{"수원시", "성남시", "고양시", "용인시", "부천시", "안산시", "안양시", "남양주시", "화성시", "평택시", "의정부시", "시흥시", "파주시", "김포시", "광명시", "광주시", "군포시", "이천시", "양주시", "오산시", "구리시", "안성시", "포천시", "의왕시", "하남시", "여주시", "양평군", "동두천시", "과천시", "가평군", "연천군"});
        regionData.put("강원도", new String[]{"춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"});
        regionData.put("충청북도", new String[]{"청주시", "충주시", "제천시", "보은군", "옥천군", "영동군", "증평군", "진천군", "괴산군", "음성군", "단양군"});
        regionData.put("충청남도", new String[]{"천안시", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"});
        regionData.put("전라북도", new String[]{"전주시", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"});
        regionData.put("전라남도", new String[]{"목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군"});
        regionData.put("경상북도", new String[]{"포항시", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"});
        regionData.put("경상남도", new String[]{"창원시", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군"});
        regionData.put("제주특별자치도", new String[]{"제주시", "서귀포시"});
    }

    private void updateSigunguSpinner(String region) {
        String[] sigunguArray = regionData.get(region);
        ArrayAdapter<String> sigunguAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sigunguArray);
        sigunguAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sigunguSpinner.setAdapter(sigunguAdapter);
    }

    private void searchTouristInfo(String region, String sigungu, int contentTypeId) {
        Call<TouristInfoResponse> call = touristService.getTouristInfo(region, sigungu, contentTypeId);
        call.enqueue(new Callback<TouristInfoResponse>() {
            @Override
            public void onResponse(Call<TouristInfoResponse> call, Response<TouristInfoResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<TouristInfoDTO> touristInfoList = response.body().getItems();
                    touristAdapter.setTouristInfoList(touristInfoList);
                } else {
                    Toast.makeText(TouristSearchActivity.this, "검색 결과가 없습니다.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<TouristInfoResponse> call, Throwable t) {
                Toast.makeText(TouristSearchActivity.this, "검색 실패: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
