package com.devdroid.easybiz.recyclerview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.model.Gift;
import com.devdroid.easybiz.model.Toy;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GiftsRecyclerViewAdapter extends RecyclerView.Adapter<GiftsRecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Gift> availableGift;

    public GiftsRecyclerViewAdapter(Context context, ArrayList<Gift> availableGift) {
        this.availableGift= availableGift;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_gift_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Gift gift= availableGift.get(position);

        Log.i("Abhiram", "onBindViewHolder: "+gift.getImage_url());
        Glide.with(context).load(gift.getImage_url()).into(holder.productImage);
        holder.productName.setText(gift.getProduct_name());
        holder.productDescription.setText(gift.getProduct_description());
        holder.giftsRemaining.setText("Gifts Remaining: "+gift.getItems_remaining());

    }

    @Override
    public int getItemCount() {
        return availableGift.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView giftsRemaining;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.giftImage);
            productName= itemView.findViewById(R.id.giftName);
            productDescription= itemView.findViewById(R.id.giftDescription);
            giftsRemaining= itemView.findViewById(R.id.giftsRemaining);
        }
    }
}
