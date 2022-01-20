package com.devdroid.easybiz.drawerActivities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.devdroid.easybiz.MainActivity;
import com.devdroid.easybiz.R;
import com.devdroid.easybiz.authentication.StartActivity;
import com.devdroid.easybiz.codes.RequestCodes;
import com.devdroid.easybiz.model.Employee;
import com.devdroid.easybiz.databaseParameters.FirebaseNodes;
import com.devdroid.easybiz.recyclerview.EmployeeRecyclerViewAdapter;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class EmployeeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EmployeeRecyclerViewAdapter adapter;
    RecyclerView employeeRecyclerView;
    Toolbar employeeToolbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference registeredUsersNode;
    FirebaseRecyclerAdapter<Employee, EmployeeRecyclerViewAdapter.ViewHolder> firebaseRecyclerAdapter;
    NavigationView navigationViewMain;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    Toolbar toolbar;
    private TextView userShopName, userEmailID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee);

        initViews();
        prepareDatabase();
        getWindow().setStatusBarColor(getResources().getColor(android.R.color.transparent));
        setSupportActionBar(employeeToolbar);
        setDrawerHeader();

        employeeToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmployeeActivity.this.finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
      else
        {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }

    private void setDrawerHeader() {

        View view = navigationViewMain.getHeaderView(0);
        userShopName = view.findViewById(R.id.header_shopName);
        userEmailID = view.findViewById(R.id.userEmail);
        Intent intent = getIntent();
        Log.i("Abhiram", "setDrawerHeader: " + intent.getStringExtra(RequestCodes.INTENT_EXTRA_SHOP_NAME));
        registeredUsersNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (auth.getCurrentUser() != null) {

                    userShopName.setText("Abhiram Shop");
                    userEmailID.setText(auth.getCurrentUser().getEmail());
                } else {
                    Map<String, Object> user = (Map<String, Object>) snapshot.child(getIntent().getStringExtra(RequestCodes.INTENT_EXTRA_SHOP_NAME)).getValue();
                    userShopName.setText(user.get(FirebaseNodes.SHOP_NAME).toString());
                    userEmailID.setText(user.get(FirebaseNodes.EMAIL).toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void prepareDatabase()
    {
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        registeredUsersNode = firebaseDatabase.getReference("Registered Users");

    }
    private void initViews() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color_main_activity));
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationViewMain = findViewById(R.id.navigation_view_main);
        employeeToolbar = findViewById(R.id.toolbar_for_activity_employee);
        employeeRecyclerView = findViewById(R.id.employee_recyclerView);
        adapter = new EmployeeRecyclerViewAdapter(this);
        employeeRecyclerView.setAdapter(adapter);
        employeeRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId())
        {
            case R.id.menu_home:
                intent= new Intent(this, MainActivity.class);
                        startActivity(intent);
                        return true;

            case R.id.menu_toys:
                intent= new Intent(this, ToysActivity.class);
                startActivity(intent);
                return true;


            case R.id.menu_gifts:
                intent = new Intent(this, GiftsActivity.class);
                startActivity(intent);
                return true;


            case R.id.menu_luxury:
                intent= new Intent(this, LuxuryActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_about_us:
                drawerLayout.closeDrawer(GravityCompat.START);
                intent= new Intent(this, AboutUsActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_data_privacy:
                intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(MainActivity.url));
                startActivity(intent);
                finish();
                return true;

            case  R.id.menu_sign_out:
                auth.signOut();
                finish();
                startActivity(new Intent(this, StartActivity.class));
                return true;

            case R.id.menu_employees:
                intent= new Intent(this, EmployeeActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                finish();
                return true;

            default:
                return false;

        }
    }
}