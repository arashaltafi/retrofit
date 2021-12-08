package com.arash.altafi.retrofit.java.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.arash.altafi.retrofit.R;
import com.arash.altafi.retrofit.java.data.ResponseItem;

import java.util.List;

public class AdapterItemJava extends RecyclerView.Adapter<AdapterItemJava.ItemViewHolder>{

    private List<ResponseItem> responseItems;

    public AdapterItemJava(List<ResponseItem> responseItems) {
        this.responseItems = responseItems;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list , parent , false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bind(responseItems.get(position));
    }

    @Override
    public int getItemCount() {
        return responseItems.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView txt_name,txt_mobile,txt_email;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_email = itemView.findViewById(R.id.txt_email_item);
            txt_name = itemView.findViewById(R.id.txt_name_item);
            txt_mobile = itemView.findViewById(R.id.txt_mobile_item);
        }

        private void bind(ResponseItem item)
        {
            txt_name.setText(item.getNamefamily());
            txt_email.setText(item.getEmail());
            txt_mobile.setText(item.getMobile());
        }

    }

}
