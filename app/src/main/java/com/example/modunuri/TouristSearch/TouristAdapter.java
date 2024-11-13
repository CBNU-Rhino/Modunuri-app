// TouristAdapter.java
package com.example.modunuri.TouristSearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.modunuri.R;
import java.util.ArrayList;
import java.util.List;

public class TouristAdapter extends RecyclerView.Adapter<TouristAdapter.TouristViewHolder> {

    private List<TouristInfoDTO> touristInfoList = new ArrayList<>();

    public void setTouristInfoList(List<TouristInfoDTO> touristInfoList) {
        this.touristInfoList = touristInfoList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TouristViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tourist_info, parent, false);
        return new TouristViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TouristViewHolder holder, int position) {
        TouristInfoDTO touristInfo = touristInfoList.get(position);
        holder.titleTextView.setText(touristInfo.getTitle());
        holder.addrTextView.setText(touristInfo.getAddr1() + " " + touristInfo.getAddr2());

        // 이미지 로드 (Glide 라이브러리 사용)
        Glide.with(holder.itemView.getContext())
                .load(touristInfo.getFirstImage()) // 이미지 URL
                .placeholder(R.drawable.placeholder_image) // 로딩 중일 때 표시할 이미지
                .error(R.drawable.error) // 에러 발생 시 표시할 이미지
                .fallback(R.drawable.placeholder_image) // URL이 null일 경우 표시할 이미지
                .into(holder.firstImageView);
    }


    @Override
    public int getItemCount() {
        return touristInfoList.size();
    }

    static class TouristViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView, addrTextView;
        ImageView firstImageView;

        public TouristViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            addrTextView = itemView.findViewById(R.id.addr_text_view);
            firstImageView = itemView.findViewById(R.id.first_image_view);
        }
    }
}
