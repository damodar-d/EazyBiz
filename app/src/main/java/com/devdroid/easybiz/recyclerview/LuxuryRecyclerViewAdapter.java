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
import com.devdroid.easybiz.model.Luxury;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LuxuryRecyclerViewAdapter extends RecyclerView.Adapter<LuxuryRecyclerViewAdapter.ViewHolder> {

    private ArrayList<Luxury>luxuryArrayList= new ArrayList<>();
    private Context context;

    public LuxuryRecyclerViewAdapter( Context context,ArrayList<Luxury> luxuryArrayList) {
        this.luxuryArrayList = luxuryArrayList;
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_luxury_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Luxury luxury= luxuryArrayList.get(holder.getAdapterPosition());

        Log.i("Abhiram", "onBindViewHolder: "+luxury.getImage_url());
        Glide.with(context).load(luxury.getImage_url()).into(holder.productImage);
        holder.productName.setText(luxury.getProduct_name());
        holder.productDescription.setText(luxury.getProduct_description());
        holder.luxuryItemsRemaining.setText("Gifts Remaining: "+ luxury.getItems_remaining());

    }

    @Override
    public int getItemCount() {

        return luxuryArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView luxuryItemsRemaining;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.luxuryImageView);
            productName= itemView.findViewById(R.id.luxuryName);
            productDescription= itemView.findViewById(R.id.luxuryDescription);
            luxuryItemsRemaining= itemView.findViewById(R.id.luxuryRemaining);
        }
    }
}
