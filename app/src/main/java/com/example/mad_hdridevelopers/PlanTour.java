package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.text.DateFormat;
import java.util.Date;

public class PlanTour extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView recyclerView;
    private FloatingActionButton floatingActionButton;

    private DatabaseReference reference;
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private String onlineuserID ;

    private ProgressDialog loader;

    private String key = "";
    private String task;
    private String description;
    private String time;

    Button cancel;

    //side Nav
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    androidx.appcompat.widget.Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_tour);



        //toolbar
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Tour Planner");

        recyclerView = findViewById(R.id.planrecyclerview);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        onlineuserID = mUser.getUid();
        reference = FirebaseDatabase.getInstance().getReference().child("Tasks").child(onlineuserID);


        floatingActionButton = findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                addTask();
            }
        });

        //side nav
        drawerLayout = findViewById(R.id.plan_layout);
        navigationView = findViewById(R.id.nav_view);

        //navigate menu side
        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //click on nav side
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.nav_trans);

    }

    private void addTask() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflator = LayoutInflater.from(this);

        View myView = inflator.inflate(R.layout.planinput_file, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        dialog.setCancelable(false);
        //dialog.show();

        final EditText task = myView.findViewById(R.id.et_plantask);
        final EditText description = myView.findViewById(R.id.et_plandesc);
        final EditText time = myView.findViewById(R.id.et_plantime);

        final Button save = myView.findViewById(R.id.planbtn_save);
        final Button cancel = myView.findViewById(R.id.planbtn_cancel);


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mTask = task.getText().toString().trim();
                String mDescription = description.getText().toString().trim();
                String mTime = time.getText().toString().trim();
                String id = reference.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());

                if (TextUtils.isEmpty(mTask)) {
                    task.setError("Task Required");
                    return;
                }
                if (TextUtils.isEmpty(mDescription)) {
                    description.setError("Description Required");
                    return;
                }
                if (TextUtils.isEmpty(mTime)) {
                    time.setError("Time is Required");
                    return;
                } else {
                    loader.setMessage("Adding Your Data");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    PlanModel planmodel = new PlanModel(mTask, mDescription, mTime, id, date);
                    reference.child(id).setValue(planmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(PlanTour.this, "Plan has been inserted successfully", Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            } else {
                                String error = task.getException().toString();
                                Toast.makeText(PlanTour.this, "failed" + error, Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });

                }
                dialog.dismiss();

            }
        });

        dialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<PlanModel> options = new FirebaseRecyclerOptions.Builder<PlanModel>()
                .setQuery(reference, PlanModel.class)
                .build();

        FirebaseRecyclerAdapter<PlanModel, MyViewHolder> adapter = new FirebaseRecyclerAdapter<PlanModel, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull  MyViewHolder holder,final int position, @NonNull final PlanModel planmodel) {
                holder.setDate(planmodel.getDate());
                holder.setTest(planmodel.getTask());
                holder.setDesc(planmodel.getDescription());
                holder.setTime(planmodel.getTime());

                //update plan task
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        key = getRef(position).getKey();
                        task = planmodel.getTask();
                        description = planmodel.getDescription();
                        time = planmodel.getTime();

                        updateTask();
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.planretrieved_layout,parent,false);
                return new MyViewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        View mView;

        public MyViewHolder(@NonNull View ItemView){
            super(ItemView);
            mView = itemView;
        }

        public void setTest(String task){
            TextView taskTestView = mView.findViewById(R.id.pretask_tv);
            taskTestView.setText(task);
        }

        public void setDesc(String desc){
            TextView descTestView = mView.findViewById(R.id.predesc_tv);
            descTestView.setText(desc);
        }

        public void setTime(String time){
            TextView timeTestView = mView.findViewById(R.id.pretime_tv);
            timeTestView.setText(time);
        }

        public void setDate(String date){
            TextView dateTestView = mView.findViewById(R.id.predate_tv);
        }

    }

   private void updateTask() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.planupdate_data, null);
        myDialog.setView(view);

        final AlertDialog dialog = myDialog.create();

        final EditText mTask = view.findViewById(R.id.pluptask_et);
        final EditText mDescription = view.findViewById(R.id.plupdesc_et);
        final EditText mTime = view.findViewById(R.id.pluptime_et);

        mTask.setText(task);
        mTask.setSelection(task.length());

        mDescription.setText(description);
        mDescription.setSelection(description.length());

        mTime.setText(time);
        mTime.setSelection(time.length());

        Button delButton = view.findViewById(R.id.plndelete_btn);
        Button updateButton = view.findViewById(R.id.plnupdate_btn);

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                task = mTask.getText().toString().trim();
                description = mDescription.getText().toString().trim();
                time = mTime.getText().toString().trim();

                String date = DateFormat.getDateInstance().format(new Date());

                PlanModel planmodel = new PlanModel(task, description, time, key, date);

                reference.child(key).setValue(planmodel).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()){
                            Toast.makeText(PlanTour.this, "Data has been updated successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            String err = task.getException().toString();
                            Toast.makeText(PlanTour.this, "update failed "+err, Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                dialog.dismiss();

            }
        });

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(PlanTour.this, "Task deleted successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            String err = task.getException().toString();
                            Toast.makeText(PlanTour.this, "Failed to delete task "+ err, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();
            }
        });

        dialog.show();
    }


    //Navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.nav_home:
                Intent intent3 = new Intent(PlanTour.this,Home.class);
                startActivity(intent3);
                break;
            case R.id.nav_trans:
                break;
            case R.id.nav_cal:
                Intent intent2 = new Intent(PlanTour.this,BudgetCal.class);
                startActivity(intent2);
                Toast.makeText(this,"Cal",Toast.LENGTH_SHORT).show();
                break;
            case R.id.nav_user:
                Intent intent4 = new Intent(PlanTour.this,UserProfile.class);
                startActivity(intent4);
                break;
            case R.id.nav_logout:
                Intent signOut = new Intent(PlanTour.this,LoginPage.class);
                startActivity(signOut);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
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
}