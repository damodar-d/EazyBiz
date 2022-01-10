package com.devdroid.easybiz.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.devdroid.easybiz.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GiftsRecyclerViewAdapter extends RecyclerView.Adapter<GiftsRecyclerViewAdapter.ViewHolder> {

    Context context;

    public GiftsRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.productImage.setImageResource(R.drawable.baribie_doll);

    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage= itemView.findViewById(R.id.itemImage);
            productName= itemView.findViewById(R.id.product_name);
            productDescription= itemView.findViewById(R.id.product_description);
        }
    }
}
