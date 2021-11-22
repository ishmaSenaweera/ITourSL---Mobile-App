package com.example.mad_hdridevelopers;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BudgetCal extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //side Nav
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;


    TextView TotalAmountTV;
    RecyclerView recyclerView;
    FloatingActionButton fab;


    private DatabaseReference ref;
    private ProgressDialog loader;
    long maxid=0;
    private TodayBudget todayBudget;
    private List<Budget> myBudgetList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_cal);

        //toolbar
        toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("iTourSL");

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
        navigationView.setCheckedItem(R.id.nav_cal);




        TotalAmountTV = findViewById(R.id.TotalAmount);

        fab = findViewById(R.id.fab);


        ref = FirebaseDatabase.getInstance().getReference().child("Budget");
        loader = new ProgressDialog(this);

        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    maxid = snapshot.getChildrenCount();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItemSpentOn();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        myBudgetList = new ArrayList<>();
        todayBudget = new TodayBudget(BudgetCal.this,myBudgetList);
        recyclerView.setAdapter(todayBudget);
        readBudget();

        //getAllBudgets();

    }
    /*private void getAllBudgets() {
        myBudgetList.clear();
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {


                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Budget budget = snapshot.getValue(Budget.class);
                    myBudgetList.add(budget);
                }

                todayBudget.notifyDataSetChanged();

                int totalAmount;
                totalAmount = 0;

                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map = (Map <String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount += pTotal;

                    TotalAmountTV.setText("Total Day Spending: Rs."+ totalAmount);
                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                todayBudget.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                todayBudget.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                todayBudget.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/


    private void readBudget(){
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Budget");
        Query query = reference.orderByChild("date").equalTo(date);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myBudgetList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Budget budget = dataSnapshot.getValue(Budget.class);
                    myBudgetList.add(budget);
                }

                todayBudget.notifyDataSetChanged();

                int totalAmount = 0;
                for (DataSnapshot ds : snapshot.getChildren()){
                    Map< String, Object> map = (Map<String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount+=pTotal;

                    TotalAmountTV.setText("Total Day's Spending Rs:"+totalAmount);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }









    /*private void readBudget(){
        myBudgetList.clear();
        //DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //Calendar cal = Calendar.getInstance();
        // String date = dateFormat.format(cal.getTime());

        ref = FirebaseDatabase.getInstance().getReference().child("Budget");
        //Query query = ref.orderByChild("date");



       ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myBudgetList.clear();

                myBudgetList.add(snapshot.getValue(Budget.class));


                todayBudget.notifyDataSetChanged();

                /*int totalAmount;
                totalAmount = 0;

                for(DataSnapshot ds: snapshot.getChildren()){
                    Map<String,Object> map = (Map <String, Object>) ds.getValue();
                    Object total = map.get("amount");
                    int pTotal = Integer.parseInt(String.valueOf(total));
                    totalAmount += pTotal;

                    TotalAmountTV.setText("Total Day Spending: Rs."+ totalAmount);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }*/

    private void addItemSpentOn() {

        AlertDialog.Builder myDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View myView = inflater.inflate(R.layout.budget_input_layout, null);
        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();
        myDialog.setCancelable(false);

        final Spinner itemsSpinner = myView.findViewById(R.id.spinner);
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.items));
        itemsAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        itemsSpinner.setAdapter(itemsAdapter);

        final EditText amount = myView.findViewById(R.id.b_amount);
        final EditText note = myView.findViewById(R.id.b_note);

        final Button cancel = myView.findViewById(R.id.b_cancel);
        final Button save = myView.findViewById(R.id.b_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mAmount = amount.getText().toString();
                String mNote = note.getText().toString();
                String item = itemsSpinner.getSelectedItem().toString();

                if(mAmount.isEmpty()){
                    amount.setError("Amount is Required");
                    return;
                }
                if(mNote.isEmpty()){
                    note.setError("Note is Required");
                    return;
                }
                if(item.equals("Select Item")){
                    Toast.makeText(BudgetCal.this, "Select a Valid Item",Toast.LENGTH_SHORT).show();
                }
                else{
                    loader.setMessage("Adding Item to Database");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    String id = ref.push().getKey();
                    DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    Calendar cal = Calendar.getInstance();
                    String date = dateFormat.format(cal.getTime());

                    Budget budget = new Budget(item, date, id, mNote,Integer.parseInt(mAmount));
                    ref.child(id).setValue(budget).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(BudgetCal.this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(BudgetCal.this, "Failed", Toast.LENGTH_SHORT).show();
                            }
                            loader.dismiss();
                        }
                    });
                }
                dialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

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
                Intent intent2 = new Intent(BudgetCal.this,Home.class);
                startActivity(intent2);
                break;
            case R.id.nav_trans:
                Intent intent1 = new Intent(BudgetCal.this,PlanTour.class);
                startActivity(intent1);
                break;
            case R.id.nav_cal:

                break;
            case R.id.nav_user:
                Intent intent4 = new Intent(BudgetCal.this,UserProfile.class);
                startActivity(intent4);
                break;
            case R.id.nav_logout:
                Intent signOut = new Intent(BudgetCal.this,LoginPage.class);
                startActivity(signOut);
                break;
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}