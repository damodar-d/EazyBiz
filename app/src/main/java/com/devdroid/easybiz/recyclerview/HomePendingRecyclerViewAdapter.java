package com.devdroid.easybiz.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.devdroid.easybiz.R;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomePendingRecyclerViewAdapter extends RecyclerView.Adapter<HomePendingRecyclerViewAdapter.ViewHolder> {

    FirebaseStorage storage= FirebaseStorage.getInstance();
    Context context;

    public HomePendingRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(context).inflate(R.layout.item_list_pending,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

//        StorageReference reference= storage.getReference("toyland.png");
//        Bitmap bitmap= BitmapFactory.decodeStream(reference.getStream().getResult().getStream());
//        holder.firmImageView.setImageBitmap(bitmap);
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView firmImageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            firmImageView= itemView.findViewById(R.id.firmImageView);
        }
    }
}
