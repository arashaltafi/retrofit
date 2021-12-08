package com.arash.altafi.retrofit.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.retrofit.R;
import com.arash.altafi.retrofit.java.data.ResponseBanner;
import com.bumptech.glide.Glide;

import java.util.List;

public class AdapterBannerJava extends RecyclerView.Adapter<AdapterBannerJava.BannerViewHolder>{

    List<ResponseBanner> list;

    public AdapterBannerJava(List<ResponseBanner> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner,parent,false);
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_banner);
        }

        private void bind(ResponseBanner responseBanners)
        {
            Glide.with(itemView.getContext()).load(responseBanners.getImage()).into(imageView);
        }

    }

}
