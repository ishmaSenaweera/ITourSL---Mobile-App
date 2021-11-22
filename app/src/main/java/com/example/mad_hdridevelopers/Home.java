package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //side Nav
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //to logout
    FirebaseAuth mAuth=FirebaseAuth.getInstance();


    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        //toolbar
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("iTourSL");


        //Buttons
        Button map = findViewById(R.id.home_map);
        Button transport = findViewById(R.id.home_vehicle);
        Button hotel = findViewById(R.id.home_hotels);
        Button shop = findViewById(R.id.home_shops);
        Button restaurant = findViewById(R.id.home_rest);

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(Home.this, CurrentLocation.class);
                startActivity(mapView);
            }
        });
        transport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(Home.this, TransportView.class);
                startActivity(mapView);
            }
        });

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(Home.this, ShopView.class);
                startActivity(mapView);
            }
        });
        restaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(Home.this, RestaurantView.class);
                startActivity(mapView);
            }
        });

        hotel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(Home.this, HotelView.class);
                startActivity(mapView);
            }
        });




        //map part
        Fragment fragment = new MapFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mapLayout,fragment)
                .commit();


        //side nav

        drawerLayout = findViewById(R.id.drawer_layout);//need to be changed
        navigationView = findViewById(R.id.nav_view);



        //navigate menu

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //click on nav
        navigationView.setNavigationItemSelectedListener(this);
        //selected icon
        navigationView.setCheckedItem(R.id.nav_home);





        //bottom navigation

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.weather:
                        startActivity(new Intent(getApplicationContext(), WeatherScreen1.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        return true;

                    case R.id.photos:
                        startActivity(new Intent(getApplicationContext(), Photos.class));
                        overridePendingTransition(0,0);
                        return true;

                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen((GravityCompat.START))){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
            super.onBackPressed();
        }
    }





    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                break;
            case R.id.nav_trans:
                Intent intent1 = new Intent(Home.this,PlanTour.class);
                startActivity(intent1);
                break;
            case R.id.nav_cal:
                Intent intent2 = new Intent(Home.this,BudgetCal.class);
                startActivity(intent2);
                break;
            case R.id.nav_user:
                Intent intent4 = new Intent(Home.this,UserProfile.class);
                startActivity(intent4);
                break;
            case R.id.nav_logout:
                mAuth.signOut();
                Intent signOut = new Intent(Home.this,LoginPage.class);
                startActivity(signOut);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }




}