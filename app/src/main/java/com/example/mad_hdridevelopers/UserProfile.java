package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class UserProfile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    //side Nav
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        Button insert = findViewById(R.id.user_btn2);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapView = new Intent(UserProfile.this, InsertImages.class);
                startActivity(mapView);
            }
        });


        //toolbar
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("iTourSL");

        //side nav

        drawerLayout = findViewById(R.id.profile_layout);
        navigationView = findViewById(R.id.nav_view);

        //navigate menu side

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //click on nav side
        navigationView.setNavigationItemSelectedListener(this);
        //selected icon
        navigationView.setCheckedItem(R.id.nav_user);

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
                Intent intent3 = new Intent(UserProfile.this,Home.class);
                startActivity(intent3);
                break;
            case R.id.nav_trans:

                break;
            case R.id.nav_cal:
                Intent intent2 = new Intent(UserProfile.this,BudgetCal.class);
                startActivity(intent2);
                break;
            case R.id.nav_user:
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }



}