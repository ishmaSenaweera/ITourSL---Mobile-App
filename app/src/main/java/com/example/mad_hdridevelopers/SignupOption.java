package com.example.mad_hdridevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SignupOption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_option);


        Button wel2btn5=findViewById(R.id.wel2btn5);
        Button user =findViewById(R.id.wel2btn4);

        wel2btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });

        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity2();
            }
        });
    }
    private void changeActivity(){
        Intent business = new Intent(this,BusinessRegistration.class);
        startActivity(business);
    }

    private void changeActivity2(){
        Intent signup = new Intent(this,UserRegistration.class);
        startActivity(signup);
    }

}