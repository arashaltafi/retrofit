package com.arash.altafi.retrofit.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.retrofit.R
import com.arash.altafi.retrofit.kotlin.data.ResponseBannerKotlin
import com.bumptech.glide.Glide

class AdapterBannerKotlin(val bannerList : List<ResponseBannerKotlin>) : RecyclerView.Adapter<BannerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_banner , parent , false)
        return BannerViewHolder(view)
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        val list = bannerList[position]
        Glide.with(holder.itemView.context).load(list.image).into(holder.imageView)
    }

    override fun getItemCount(): Int = bannerList.size

}

class BannerViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    val imageView : ImageView = itemView.findViewById(R.id.img_banner)

}