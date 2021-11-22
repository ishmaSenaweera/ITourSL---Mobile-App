package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginPage extends AppCompatActivity {

    EditText name;
    EditText pass;
    Button login;
    ProgressDialog mLoadingBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login = (Button)findViewById(R.id.loginbtn001);
        name = (EditText) findViewById(R.id.logedit1);
        pass = (EditText) findViewById(R.id.logedit2);

        login.setOnClickListener((v) -> {checkCredentials();});
        mAuth = FirebaseAuth.getInstance();
        mLoadingBar=new ProgressDialog(LoginPage.this);

    }

    private void checkCredentials(){

        if(name.length()==0){
            name.setError("Enter your email!");
        }
        else if(pass.length()==0){
            pass.setError("Enter your password!");
        }
        else{

            String email2 = name.getText().toString();
            String password2 = pass.getText().toString();

            mLoadingBar.setTitle("Login");
            mLoadingBar.setMessage("please wait");
            mLoadingBar.setCanceledOnTouchOutside(false);
            mLoadingBar.show();

            mAuth.signInWithEmailAndPassword(email2,password2).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        mLoadingBar.dismiss();
                        Intent afterLogin = new Intent(LoginPage.this,Home.class);
                        startActivity(afterLogin);

                    }
                    else{
                        mLoadingBar.dismiss();
                        Toast.makeText(LoginPage.this,"Your email or password is wrong!",Toast.LENGTH_SHORT).show();
                    }
                }
            });

        }


    }
}