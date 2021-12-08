package com.arash.altafi.retrofit.kotlin.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arash.altafi.retrofit.R
import com.arash.altafi.retrofit.kotlin.data.ResponseItemKotlin

class AdapterItemKotlin(val itemList : List<ResponseItemKotlin>) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_list , parent , false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val list = itemList[position]
        holder.txtEmail.text = list.email
        holder.txtName.text = list.namefamily
        holder.txtMobile.text = list.mobile
    }

    override fun getItemCount(): Int = itemList.size

}

class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

    var txtEmail : TextView = itemView.findViewById(R.id.txt_email_item)
    var txtName : TextView = itemView.findViewById(R.id.txt_name_item)
    var txtMobile : TextView = itemView.findViewById(R.id.txt_mobile_item)

}