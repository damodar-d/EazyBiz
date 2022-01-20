package com.devdroid.easybiz.drawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.devdroid.easybiz.App;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.model.Gift;
import com.devdroid.easybiz.recyclerview.GiftsRecyclerViewAdapter;
import com.devdroid.easybiz.recyclerview.ToysListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class GiftsActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView giftsRecyclerView;
    private ArrayList<Gift>availableGiftsList;
    DatabaseReference giftsNode;
    GiftsRecyclerViewAdapter giftsRecyclerViewAdapter;
    private OnGiftItemClickListener listener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);

        getReferences();
        initViews();
        setUpToolbar();
     toolbar.setNavigationOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             GiftsActivity.this.finish();
         }
     });

     availableGiftsList= new ArrayList<>();
     new Thread(new Runnable() {
         @Override
         public void run() {
             giftsNode.addValueEventListener(new ValueEventListener() {
                 @Override
                 public void onDataChange(@NonNull DataSnapshot snapshot) {

                     for(DataSnapshot snapshot1: snapshot.getChildren())
                     {
                         Log.i("Abhiram", "onDataChange: "+Thread.currentThread().getName());
                         Gift gift= snapshot1.getValue(Gift.class);
                         availableGiftsList.add(gift);
                     }
                     setAdapter();
                     setManager();
                     giftsRecyclerViewAdapter.notifyDataSetChanged();
                 }

                 @Override
                 public void onCancelled(@NonNull DatabaseError error) {

                 }
             });
         }
     }).start();
    }

    private void setUpToolbar()
    {
        setSupportActionBar(toolbar);
    }

    private void initViews()
    {
        toolbar= findViewById(R.id.toolbar_for_activity_gifts);
        giftsRecyclerView= findViewById(R.id.giftsRecyclerView);
    }

    private void setAdapter()
    {
        Log.i("Abhiram", "setAdapter: "+availableGiftsList.size());
        giftsRecyclerViewAdapter= new GiftsRecyclerViewAdapter(GiftsActivity.this,availableGiftsList);
        giftsRecyclerView.setAdapter(giftsRecyclerViewAdapter);
    }

    private void getReferences()
    {
        giftsNode= App.rootNode.getReference("Gifts");

    }

    private void setManager()
    {
        giftsRecyclerView.setLayoutManager(new LinearLayoutManager(GiftsActivity.this));
    }

    public interface OnGiftItemClickListener
    {
        int intPosition(int position);
    }
}