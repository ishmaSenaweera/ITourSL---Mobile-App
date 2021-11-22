package com.example.mad_hdridevelopers;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class TodayBudget extends RecyclerView.Adapter<TodayBudget.viewHolder>{

    private Context bContext;
    private List<Budget> myBudgetList;
    private String postid;
    private String note;
    private int amount;
    private String item;

    public TodayBudget(Context bContext, List<Budget> myBudgetList) {
        this.bContext = bContext;
        this.myBudgetList = myBudgetList;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(bContext).inflate(R.layout.budget_output_layout,parent,false);
        return new TodayBudget.viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        final Budget budget = myBudgetList.get(position);
        holder.item.setText("Item: "+budget.getItem());
        holder.date.setText("Date: "+budget.getDate());
        holder.notes.setText("Notes: "+budget.getNotes());
        holder.amount.setText("Amount: "+budget.getAmount());

        switch (budget.getItem()){
            case "Food and Beverages":
                holder.imageView.setImageResource(R.drawable.budget_food);
                break;
            case "Transportation":
                holder.imageView.setImageResource(R.drawable.budget_transport);
                break;
            case "Vehicle":
                holder.imageView.setImageResource(R.drawable.budget_vehicle);
                break;
            case "Accomodation":
                holder.imageView.setImageResource(R.drawable.budget_accomodation);
                break;
            case "Others":
                holder.imageView.setImageResource(R.drawable.budget_other);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + budget.getItem());
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                postid = budget.getId();
                note = budget.getNotes();
                amount = budget.getAmount();
                item = budget.getItem();

                updateData();
            }
        });

    }

    private void updateData() {
        AlertDialog.Builder myDialog = new AlertDialog.Builder(bContext);
        LayoutInflater inflater = LayoutInflater.from(bContext);
        View myView = inflater.inflate(R.layout.budgetcal_update, null);

        myDialog.setView(myView);

        final AlertDialog dialog = myDialog.create();

        final  TextView mItem = myView.findViewById(R.id.updateitem);
        final EditText mAmount = myView.findViewById(R.id.updateamount);
        final EditText mNote = myView.findViewById(R.id.updatenote);

        mItem.setText(item);

        mAmount.setText(String.valueOf(amount));
        mAmount.setSelection(String.valueOf(amount).length());

        mNote.setText(note);
        mNote.setSelection(note.length());

        Button updateBtn  = myView.findViewById(R.id.b_update);
        Button deleteBtn = myView.findViewById(R.id.b_delete);

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = Integer.parseInt(mAmount.getText().toString());
                note = mNote.getText().toString();

                DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Calendar cal = Calendar.getInstance();
                String date = dateFormat.format(cal.getTime());

                Budget budget = new Budget(item, date,postid, note, amount);
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Budget");
                reference.child(postid).setValue(budget).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(bContext, "Updated successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(bContext, "failed " +task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.dismiss();
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Budget");
                reference.child(postid).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(bContext, "Deleted successfully", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(bContext, "failed to delete " +task.getException(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                dialog.dismiss();

            }
        });


        dialog.show();






    }

    @Override
    public int getItemCount() {
        return myBudgetList.size();

    }

    public class viewHolder extends RecyclerView.ViewHolder{
        public TextView item,amount,date ,notes;
        public ImageView imageView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            item = itemView.findViewById(R.id.bItem);
            amount = itemView.findViewById(R.id.bAmount);
            notes = itemView.findViewById(R.id.bNote);
            date = itemView.findViewById(R.id.bDate);
            imageView = itemView.findViewById(R.id.budgetImageView);


        }
    }
}
