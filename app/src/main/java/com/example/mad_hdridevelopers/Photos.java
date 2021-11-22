package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.List;

public class Photos extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //side Nav
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    //to logout
    FirebaseAuth mAuth=FirebaseAuth.getInstance();

    BottomNavigationView bottomNavigationView;

    //retrieve data
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    androidx.recyclerview.widget.RecyclerView recyclerView;
    UserAdapter userAdapter;
    List<UserModel> userModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);

        //toolbar
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("iTourSL");




        //recyclerview

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("UserImages");
        mStorage = FirebaseStorage.getInstance();
        recyclerView = findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userModelList = new ArrayList<UserModel>();
        userAdapter = new UserAdapter(Photos.this,userModelList);
        recyclerView.setAdapter(userAdapter);


        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                UserModel userModel = snapshot.getValue(UserModel.class);
                userModelList.add(userModel);
                userAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        //side nav

        drawerLayout = findViewById(R.id.photos_layout);
        navigationView = findViewById(R.id.nav_view);

        //navigate menu side

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //click on nav side
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_home);






        //Bottom Navigation

        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.photos);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.weather:
                        startActivity(new Intent(getApplicationContext(), WeatherScreen1.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), Home.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.photos:
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
                Intent intent3 = new Intent(Photos.this,Home.class);
                startActivity(intent3);
                break;
            case R.id.nav_trans:
                Intent intent1 = new Intent(Photos.this,PlanTour.class);
                startActivity(intent1);
                break;
            case R.id.nav_cal:
                Intent intent2 = new Intent(Photos.this,BudgetCal.class);
                startActivity(intent2);
                Toast.makeText(this,"Cal",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_user:
                Intent intent4 = new Intent(Photos.this,UserProfile.class);
                startActivity(intent4);
                break;
            case R.id.nav_logout:
                mAuth.signOut();
                Intent signOut = new Intent(Photos.this,LoginPage.class);
                startActivity(signOut);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;

    }
}