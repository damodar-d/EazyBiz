package com.devdroid.easybiz;


import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.devdroid.easybiz.authentication.StartActivity;
import com.devdroid.easybiz.codes.RequestCodes;
import com.devdroid.easybiz.databaseParameters.FirebaseNodes;
import com.devdroid.easybiz.dialogFragments.AccountDialogFragment;
import com.devdroid.easybiz.drawerActivities.AboutUsActivity;
import com.devdroid.easybiz.drawerActivities.EmployeeActivity;
import com.devdroid.easybiz.drawerActivities.GiftsActivity;
import com.devdroid.easybiz.drawerActivities.LuxuryActivity;
import com.devdroid.easybiz.drawerActivities.ProvidersActivity;
import com.devdroid.easybiz.drawerActivities.RecentPurchasesActivity;
import com.devdroid.easybiz.drawerActivities.ToysActivity;
import com.devdroid.easybiz.recyclerview.HomeAcceptedRecyclerViewAdapter;
import com.devdroid.easybiz.recyclerview.HomePendingRecyclerViewAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    ActionBarDrawerToggle toggle;
    AlertDialog.Builder cancelAlertDialog,signOutDialog;
    BottomNavigationView bottomNavigationView;
    DatabaseReference userEmailsNode,acceptedNode,pendingNode,registeredUsersNode;
    DrawerLayout drawerLayout;
    FirebaseAuth auth;
    FirebaseDatabase database;
    FragmentManager fragmentManager;
    HomeAcceptedRecyclerViewAdapter homeAcceptedRecyclerViewAdapter;
    HomePendingRecyclerViewAdapter homePendingRecyclerViewAdapter;
    Intent intent;
    NavigationView navigationViewMain;
    NotificationManager notificationManager;
    RecyclerView mainActivityRecyclerView;
    TextView userEmailID,userShopName;
    Toolbar toolbar;
    public static String url="https://sites.google.com/view/easybiz-privacy-policy/home";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance();
        registeredUsersNode= database.getReference("Registered Users");
        acceptedNode=database.getReference("Accepted");
        pendingNode=database.getReference("Pending");
        auth= FirebaseAuth.getInstance();
        initViews();
        setAdapters();
        getManagers();
        setDrawerHeader();
        cancelAlertDialog= buildDoYouWantToExitDialog();
        signOutDialog=buildAreYouSureYouWantToSignOutDialog();
        setSupportActionBar(toolbar);
        toggle= new ActionBarDrawerToggle(this,drawerLayout,R.string.open_navigation_drawer,R.string.close_navigation_drawer);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationViewMain.setNavigationItemSelectedListener(this);
//        binding = ActivityMainBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
//        ViewPager viewPager = binding.viewPager;
//        viewPager.setAdapter(sectionsPagerAdapter);
//        TabLayout tabs = binding.tabs;
//        tabs.setupWithViewPager(viewPager);
//        FloatingActionButton fab = binding.fab;

//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        mainActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mainActivityRecyclerView.setAdapter(homeAcceptedRecyclerViewAdapter);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.bottom_nav_accepted:
                        mainActivityRecyclerView.setAdapter(homeAcceptedRecyclerViewAdapter);
                        return true;
                    case R.id.bottom_nav_pending:
                        mainActivityRecyclerView.setAdapter(homePendingRecyclerViewAdapter);
                        return true;
                    default:
                        return false;
                }
            }
        });

    }

    private void setDrawerHeader() {

        View view= navigationViewMain.getHeaderView(0);
        userShopName= view.findViewById(R.id.header_shopName);
        userEmailID= view.findViewById(R.id.userEmail);
        Intent intent= getIntent();
        Log.i("Abhiram", "setDrawerHeader: "+ intent.getStringExtra(RequestCodes.INTENT_EXTRA_SHOP_NAME));
        registeredUsersNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(auth.getCurrentUser()!=null) {

                    userShopName.setText("Abhiram Shop");
                    userEmailID.setText(auth.getCurrentUser().getEmail());
                }
                else
                {
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Intent intent;
        switch (item.getItemId())
        {
            case R.id.menu_toys:
                intent= new Intent(this, ToysActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;


            case R.id.menu_gifts:
                intent = new Intent(this, GiftsActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;


            case R.id.menu_luxury:
                intent= new Intent(this, LuxuryActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;

            case R.id.menu_about_us:
                drawerLayout.closeDrawer(GravityCompat.START);
                intent= new Intent(this, AboutUsActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;

            case R.id.menu_data_privacy:
                intent= new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;

            case  R.id.menu_sign_out:
                auth.signOut();
                finish();
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(new Intent(this, StartActivity.class));
                return true;

            case R.id.menu_employees:
                intent= new Intent(this, EmployeeActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;

            case R.id.menu_providers:
                intent= new Intent(this, ProvidersActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;

            case R.id.menu_recent_purchases:
                intent= new Intent(this, RecentPurchasesActivity.class);
                drawerLayout.closeDrawer(GravityCompat.START);
                startActivity(intent);
                return true;


            default:
                return false;

        }
    }


    @Override
    public void onBackPressed() {


        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            cancelAlertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.super.onBackPressed();
                }
            })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
            cancelAlertDialog.create().show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        getMenuInflater().inflate(R.menu.toolbar_menu,menu);
        MenuItem account_button= menu.getItem(0);
        account_button.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Log.i("Abhiram", "onMenuItemClick: call received");
                AccountDialogFragment accountDialogFragment= new AccountDialogFragment();
                accountDialogFragment.show(fragmentManager,"Account Fragment");
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);

    }


    private void getManagers()
    {
        fragmentManager= getSupportFragmentManager();
        notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void initViews()
    {
        getWindow().setStatusBarColor(getResources().getColor(R.color.status_bar_color_main_activity));
        toolbar= findViewById(R.id.toolbar);
        drawerLayout= findViewById(R.id.drawer_layout);
        navigationViewMain= findViewById(R.id.navigation_view_main);
        mainActivityRecyclerView= findViewById(R.id.recyclerView);
        bottomNavigationView= findViewById(R.id.main_bottom_navigation);
        mainActivityRecyclerView.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);
    }

    private void setAdapters()
    {
        mainActivityRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        homePendingRecyclerViewAdapter= new HomePendingRecyclerViewAdapter(this);
        homeAcceptedRecyclerViewAdapter= new HomeAcceptedRecyclerViewAdapter(this);
    }

    private AlertDialog.Builder buildDoYouWantToExitDialog()
    {
        AlertDialog.Builder builder= new AlertDialog.Builder(this);
        return builder.setTitle("Exit!")
                .setMessage("Are you sure you want to exit ?");
    }


    public  AlertDialog.Builder buildAreYouSureYouWantToSignOutDialog()
    {
        return new AlertDialog.Builder(this)
                .setTitle("Sign Out ?")
                .setMessage("Are you Sure you want to Sign Out ?");
    }

}