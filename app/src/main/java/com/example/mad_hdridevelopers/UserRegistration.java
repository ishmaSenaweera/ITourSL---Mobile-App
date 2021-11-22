package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.InputStream;
import java.util.Random;

import kotlin.time.ExperimentalTime;

public class UserRegistration extends AppCompatActivity {

    EditText name,description,email,password;
    Uri filepath;
    ImageView image;
    Button choose,signup;
    Bitmap bitmap;
    //Authentication
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        name = (EditText) findViewById(R.id.userEdit1);
        description = (EditText) findViewById(R.id.userEdit2);
        email = (EditText) findViewById(R.id.userEdit3);
        password = (EditText) findViewById(R.id.userEdit4);


        image = (ImageView) findViewById(R.id.imageView3);
        signup = (Button) findViewById(R.id.userbtn002);
        choose = (Button) findViewById(R.id.userbtn001);

        choose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Dexter.withActivity(UserRegistration.this)
                        .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(Intent.ACTION_PICK);
                                intent.setType("image/*");
                                startActivityForResult(Intent.createChooser(intent,"Select Image"),1);
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {

                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }).check();
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(name.length() == 0 ){
                    name.setError("Enter your name!");
                }
                else if(description.length()==0){
                    description.setError("Enter Description!");
                }
                else if(email.length()==0){
                    email.setError("Enter Email Correctly!");
                }
                else if(password.length()==0){
                    password.setError("Enter Password!");
                }
                else if(password.length()<6){
                    password.setError("Your password must contain more than 6 digits!");
                }
                else{
                    uploadtofirebase();
                    navigateToLogin();
                }

            }
        });



    }

    public void navigateToLogin(){
        Intent afterSignup = new Intent(this,LoginPage.class);
        startActivity(afterSignup);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if(requestCode==1 && resultCode==RESULT_OK){
            filepath=data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(filepath);
                bitmap = BitmapFactory.decodeStream(inputStream);
                image.setImageBitmap(bitmap);
            }catch (Exception ex){

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    private void uploadtofirebase() {
        ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("File Uploader");
        dialog.show();

        name = (EditText) findViewById(R.id.userEdit1);
        description = (EditText) findViewById(R.id.userEdit2);
        email = (EditText) findViewById(R.id.userEdit3);
        password = (EditText) findViewById(R.id.userEdit4);

        //Authentication
        String Email= email.getText().toString();
        String Password=password.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(Email,Password)
                .addOnCompleteListener(UserRegistration.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            email.setText("");
                            password.setText("");
                            Toast.makeText(getApplicationContext(),"Registered Successfully!",Toast.LENGTH_LONG).show();
                        }
                        else{
                            email.setText("");
                            password.setText("");
                            Toast.makeText(getApplicationContext(),"Process Error!",Toast.LENGTH_LONG).show();
                        }
                    }
                });


        //normal process
        FirebaseStorage storage= FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("Image1"+new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                dialog.dismiss();
                                FirebaseDatabase db= FirebaseDatabase.getInstance();
                                DatabaseReference root=db.getReference("User");

                                User obj=new User(
                                        name.getText().toString(),
                                        description.getText().toString(),
                                        email.getText().toString(),
                                        password.getText().toString(),
                                        uri.toString());
                                root.child(name.getText().toString()).setValue(obj);

                                name.setText("");
                                description.setText("");
                                email.setText("");
                                password.setText("");
                                image.setImageResource(R.drawable.ic_launcher_background);
                                Toast.makeText(getApplicationContext(),"uploaded",Toast.LENGTH_SHORT).show();


                            }
                        });
                    }
                })
                .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                        float percent = (100*snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                        dialog.setMessage("Uploading :"+(int) percent+" %");
                    }
                });

    }



}