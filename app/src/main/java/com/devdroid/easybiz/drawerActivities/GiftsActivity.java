package com.devdroid.easybiz.drawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.devdroid.easybiz.R;
import com.devdroid.easybiz.recyclerview.GiftsRecyclerViewAdapter;

public class GiftsActivity extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView giftsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gifts);

        initViews();
        setUpToolbar();
     toolbar.setNavigationOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             GiftsActivity.this.finish();
         }
     });

        GiftsRecyclerViewAdapter adapter= new GiftsRecyclerViewAdapter(this);
        giftsRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        giftsRecyclerView.setAdapter(adapter);
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
}