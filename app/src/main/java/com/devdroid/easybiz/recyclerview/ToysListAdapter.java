package com.devdroid.easybiz.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devdroid.easybiz.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToysListAdapter extends RecyclerView.Adapter<ToysListAdapter.ViewHolder>{


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView product_image;
        TextView product_name;
        TextView product_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image= itemView.findViewById(R.id.itemImage);
            product_name= itemView.findViewById(R.id.product_name);
            product_description= itemView.findViewById(R.id.product_description);
        }
    }
}
