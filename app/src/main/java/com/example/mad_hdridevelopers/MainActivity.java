package com.example.mad_hdridevelopers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button signup = findViewById(R.id.wbtn2);
        Button login = findViewById(R.id.wbtn1);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeActivity2();
            }
        });

    }

    private void changeActivity(){
        Intent signup = new Intent(this,SignupOption.class);
        startActivity(signup);
    }
    private void changeActivity2(){
        Intent login = new Intent(this,LoginPage.class);
        startActivity(login);
    }

}