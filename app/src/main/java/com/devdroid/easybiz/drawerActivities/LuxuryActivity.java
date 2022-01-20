package com.devdroid.easybiz.drawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.devdroid.easybiz.App;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.model.Gift;
import com.devdroid.easybiz.model.Luxury;
import com.devdroid.easybiz.recyclerview.GiftsRecyclerViewAdapter;
import com.devdroid.easybiz.recyclerview.LuxuryRecyclerViewAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LuxuryActivity extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<Luxury> luxuryItemsList;
    DatabaseReference luxuryNode;
    LuxuryRecyclerViewAdapter luxuryRecyclerViewAdapter;
    private RecyclerView luxuryRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luxury);


        initViews();
        getReferences();
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LuxuryActivity.this.finish();
            }
        });



        luxuryItemsList= new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                luxuryNode.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot snapshot1: snapshot.getChildren())
                        {
                            Log.i("Abhiram", "onDataChange: "+Thread.currentThread().getName());
                            Luxury luxury= snapshot1.getValue(Luxury.class);
                            luxuryItemsList.add(luxury);
                        }
                        setAdapter();
                        setManager();
                        luxuryRecyclerViewAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }).start();
    }

    private void setManager() {
        luxuryRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setAdapter() {

        luxuryRecyclerViewAdapter= new LuxuryRecyclerViewAdapter(this,luxuryItemsList);
        luxuryRecyclerView.setAdapter(luxuryRecyclerViewAdapter);
    }

    private void getReferences() {

        Thread thread= new Thread(new Runnable() {
            @Override
            public void run() {

                luxuryNode= App.rootNode.getReference("Luxury");
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void initViews()
    {
        toolbar= findViewById(R.id.toolbar_for_activity_luxury);
        luxuryRecyclerView= findViewById(R.id.luxury_recyclerView);

    }
}