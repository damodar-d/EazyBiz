package com.devdroid.easybiz.drawerActivities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.devdroid.easybiz.App;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.model.Toy;
import com.devdroid.easybiz.recyclerview.ToysListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ToysActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView toysRecyclerView;
    ArrayList<Toy>availableToysList;
    DatabaseReference toysNode;
    ToysListAdapter toysListAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toys);


        getReferences();
        initViews();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToysActivity.this.finish();
            }
        });

        availableToysList= new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                toysNode.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        for(DataSnapshot snapshot1: snapshot.getChildren())
                        {
                            Toy toy= snapshot1.getValue(Toy.class);
                            availableToysList.add(toy);
                        }
                        setAdapter();
                        setManager();
                        toysListAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        }).start();


    }


    private void initViews()
    {
        toolbar = findViewById(R.id.toolbar_for_activity_toys);
        setSupportActionBar(toolbar);
        toysRecyclerView= findViewById(R.id.toysRecyclerView);
    }

    private void setAdapter()
    {
        toysListAdapter= new ToysListAdapter(ToysActivity.this,availableToysList);
        toysRecyclerView.setAdapter(toysListAdapter);
    }

    private void getReferences()
    {
        toysNode= App.rootNode.getReference("Toys");

    }

    private void setManager()
    {
        toysRecyclerView.setLayoutManager(new GridLayoutManager(ToysActivity.this,2));
    }
}