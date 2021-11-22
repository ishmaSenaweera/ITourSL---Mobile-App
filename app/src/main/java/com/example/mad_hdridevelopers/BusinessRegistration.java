package com.example.mad_hdridevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class BusinessRegistration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_registration);


        Button button1=findViewById(R.id.button);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityToHotel();
            }
        });

        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityToRestaurant();
            }
        });

        Button button3=findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivityToShop();
            }
        });

        Button button4=findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });
    }

    private void changeActivityToHotel(){
        Intent hotelreg = new Intent(this,HotelRegistration.class);
        startActivity(hotelreg);
    }

    private void changeActivityToRestaurant(){
        Intent restaurantreg = new Intent(this,RestaurantRegistration.class);
        startActivity(restaurantreg);
    }

    private void changeActivityToShop(){
        Intent shopreg = new Intent(this,ShopRegistration.class);
        startActivity(shopreg);
    }


    private void changeActivity(){
        Intent transreg = new Intent(this,TransportProviderReg.class);
        startActivity(transreg);
    }
}