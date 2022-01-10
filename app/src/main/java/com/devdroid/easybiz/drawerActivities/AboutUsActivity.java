package com.devdroid.easybiz.drawerActivities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.devdroid.easybiz.R;

public class AboutUsActivity extends AppCompatActivity {


        Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar= findViewById(R.id.toolbar_for_back_activity);
        setSupportActionBar(toolbar);
        toolbar.setNavigationContentDescription("Back");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AboutUsActivity.this.finish();
            }
        });


    }
}