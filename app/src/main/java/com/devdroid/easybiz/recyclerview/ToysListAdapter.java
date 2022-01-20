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
import com.devdroid.easybiz.model.Toy;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ToysListAdapter extends RecyclerView.Adapter<ToysListAdapter.ViewHolder>{

    ArrayList<Toy>availableToysList;
    Context context;

    public ToysListAdapter(Context context,ArrayList<Toy> availableToysList) {
        this.context= context;
        this.availableToysList = availableToysList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_toy_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Toy toy= availableToysList.get(position);

        Log.i("Abhiram", "onBindViewHolder: "+toy.getImage_url());
        Glide.with(context).load(toy.getImage_url()).into(holder.product_image);
        holder.product_name.setText(toy.getProduct_name());
        holder.product_description.setText(toy.getProduct_description());
    }

    @Override
    public int getItemCount() {
        return availableToysList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView product_image;
        TextView product_name;
        TextView product_description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            product_image= itemView.findViewById(R.id.productImage);
            product_name= itemView.findViewById(R.id.product_name);
            product_description= itemView.findViewById(R.id.product_description);
        }
    }
}
