package com.devdroid.easybiz.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.model.Accepted;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAcceptedRecyclerViewAdapter extends RecyclerView.Adapter<HomeAcceptedRecyclerViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Accepted> acceptedArrayList;
    public HomeAcceptedRecyclerViewAdapter(Context context,ArrayList<Accepted>acceptedArrayList) {
        this.context= context;
        this.acceptedArrayList=acceptedArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new ViewHolder(inflater.inflate(R.layout.item_list_accepted,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Accepted accepted= acceptedArrayList.get(position);

        Glide.with(context).load(accepted.getImage_url()).into(holder.acceptedImageView);
        holder.modeOfTransaction.setText(context.getResources().getString(R.string.mode_of_transaction)+accepted.getMode_of_transaction());
        holder.distributorName.setText(accepted.getName());
        holder.dateOfaDelivery.setText(context.getResources().getString(R.string.date_delivered)+accepted.getDate_of_delivery());
        holder.paidAmount.setText(context.getResources().getString(R.string.paid_amount)+accepted.getPaid_amount());


    }

    @Override
    public int getItemCount() {
        return acceptedArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView acceptedImageView;
        TextView distributorName;
        TextView dateOfaDelivery;
        TextView paidAmount;
        TextView modeOfTransaction;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            acceptedImageView=itemView.findViewById(R.id.pendingDistributorImage);
            distributorName= itemView.findViewById(R.id.acceptedDistributorName);
            dateOfaDelivery= itemView.findViewById(R.id.acceptedDateOfDelivery);
            paidAmount= itemView.findViewById(R.id.acceptedPaidAmount);
            modeOfTransaction=itemView.findViewById(R.id.modeOfTransaction);
        }
    }
}
