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
    private OnItemClickListener listener;

    // OnItemClickListener 인터페이스 정의
    public interface OnItemClickListener {
        void onItemClick(TouristInfoDTO touristInfo);
    }

    // 리스너 설정 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

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

        // 이미지 로드 (Glide 사용)
        Glide.with(holder.itemView.getContext())
                .load(touristInfo.getFirstImage())
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.error)
                .fallback(R.drawable.placeholder_image)
                .into(holder.firstImageView);

        // 아이템 클릭 이벤트 설정
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onItemClick(touristInfo);
            }
        });
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
