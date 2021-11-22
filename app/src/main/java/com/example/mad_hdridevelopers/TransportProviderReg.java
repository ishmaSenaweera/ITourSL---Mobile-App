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
import com.google.android.material.textfield.TextInputEditText;
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


public class TransportProviderReg extends AppCompatActivity {

    TextInputEditText name,description,address,contactno,location,url,email,password;
    Uri filepath;
    ImageView image;
    Button choose,signup;
    Bitmap bitmap;

    //Authentication
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport_provider_reg);

        name = (TextInputEditText) findViewById(R.id.trans_reg_name);
        description = (TextInputEditText) findViewById(R.id.trans_reg_desc);
        address = (TextInputEditText) findViewById(R.id.trans_reg_address);
        contactno = (TextInputEditText) findViewById(R.id.trans_reg_contact);
        location = (TextInputEditText) findViewById(R.id.trans_reg_location);
        url = (TextInputEditText) findViewById(R.id.trans_reg_url);
        email = (TextInputEditText) findViewById(R.id.trans_reg_email);
        password = (TextInputEditText) findViewById(R.id.trans_reg_password);

        image = (ImageView) findViewById(R.id.choosedimagetrans);
        signup = (Button) findViewById(R.id.transCreateBtn);
        choose = (Button) findViewById(R.id.transchoosebtn);

        choose.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Dexter.withActivity(TransportProviderReg.this)
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
                if(name.length()==0){
                    name.setError("Enter your name!");
                }
                else if(description.length()==0){
                    description.setError("Enter description!");
                }
                else if(address.length()==0){
                    address.setError("Enter Address!");
                }
                else if(contactno.length()==0){
                    contactno.setError("Enter Contact Number!");
                }
                else if(location.length()==0){
                    location.setError("Enter Location!");
                }
                else if(email.length()==0){
                    email.setError("Enter your email!");
                }
                else if(password.length()==0){
                    password.setError("Enter your password!");
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

        name = (TextInputEditText) findViewById(R.id.trans_reg_name);
        description = (TextInputEditText) findViewById(R.id.trans_reg_desc);
        address = (TextInputEditText) findViewById(R.id.trans_reg_address);
        contactno = (TextInputEditText) findViewById(R.id.trans_reg_contact);
        location = (TextInputEditText) findViewById(R.id.trans_reg_location);
        url = (TextInputEditText) findViewById(R.id.trans_reg_url);
        email = (TextInputEditText) findViewById(R.id. trans_reg_email);
        password = (TextInputEditText) findViewById(R.id. trans_reg_password);


        //Authentication
        String Email5= email.getText().toString();
        String Password5=password.getText().toString();

        mAuth = FirebaseAuth.getInstance();

        mAuth.createUserWithEmailAndPassword(Email5,Password5)
                .addOnCompleteListener(TransportProviderReg.this, new OnCompleteListener<AuthResult>() {
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



        FirebaseStorage storage= FirebaseStorage.getInstance();
        StorageReference uploader = storage.getReference("Transport1"+new Random().nextInt(50));

        uploader.putFile(filepath)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        uploader.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                dialog.dismiss();
                                FirebaseDatabase db= FirebaseDatabase.getInstance();
                                DatabaseReference root=db.getReference("TransportProvider");

                                TransportProvider obj=new TransportProvider(
                                        name.getText().toString(),
                                        description.getText().toString(),
                                        address.getText().toString(),
                                        contactno.getText().toString(),
                                        location.getText().toString(),
                                        url.getText().toString(),
                                        email.getText().toString(),
                                        password.getText().toString(),
                                        uri.toString());
                                root.child(name.getText().toString()).setValue(obj);

                                name.setText("");
                                description.setText("");
                                address.setText("");
                                contactno.setText("");
                                location.setText("");
                                url.setText("");
                                image.setImageResource(R.drawable.ic_launcher_background);
                                email.setText("");
                                password.setText("");
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
