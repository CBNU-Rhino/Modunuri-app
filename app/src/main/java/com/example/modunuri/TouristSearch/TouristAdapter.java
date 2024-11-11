package com.example.modunuri.TouristSearch;

// TouristAdapter.java
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.modunuri.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TouristAdapter extends RecyclerView.Adapter<TouristAdapter.TouristViewHolder> {

    private List<TouristInfoDTO> touristInfoList = new ArrayList<>();

    public void setTouristInfoList(List<TouristInfoDTO> list) {
        this.touristInfoList = list;
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
        TouristInfoDTO info = touristInfoList.get(position);
        holder.title.setText(info.getTitle());
        holder.address.setText(info.getAddr1());

        // 이미지 로딩
        if (!info.getFirstImage().isEmpty()) {
            Picasso.get().load(info.getFirstImage()).into(holder.image);
        } else {
            holder.image.setImageResource(R.drawable.placeholder_image); // 기본 이미지 설정
        }
    }

    @Override
    public int getItemCount() {
        return touristInfoList.size();
    }

    public static class TouristViewHolder extends RecyclerView.ViewHolder {
        TextView title, address;
        ImageView image;

        public TouristViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tourist_title);
            address = itemView.findViewById(R.id.tourist_address);
            image = itemView.findViewById(R.id.tourist_image);
        }
    }
}
