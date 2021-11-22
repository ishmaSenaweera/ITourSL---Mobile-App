package com.example.mad_hdridevelopers;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
public class RestAdapter extends FirebaseRecyclerAdapter<Restaurant,RestAdapter.myViewHolder> {
    public RestAdapter(@NonNull FirebaseRecyclerOptions<Restaurant> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull RestAdapter.myViewHolder holder, int position, @NonNull Restaurant model) {
        holder.restName.setText(model.getRestName());
        holder.restDescription.setText(model.getRestDescription());

        holder.restContactNo.setText(model.getRestContactNo());
        holder.restLocation.setText(model.getRestLocation());

        Glide.with(holder.img.getContext()).load(model.getRestImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

        holder.img.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                AppCompatActivity activity =(AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.rest_wrapper,new restdescfragment(model.getRestName(),model.getRestDescription(),model.getRestAddress(),model.getRestContactNo(),model.getRestLocation(),model.getRestUrl(),model.getRestEmail(), model.getRestImage())).addToBackStack(null).commit();
            }
        });
    }

    @NonNull
    @Override
    public RestAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_item,parent,false);
        return new RestAdapter.myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView restName,restDescription,restContactNo,restLocation,email;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.restrecycleimage1);
            restName = (TextView)itemView.findViewById(R.id.restname);
            restDescription = (TextView)itemView.findViewById(R.id.restdesc);
            restContactNo = (TextView)itemView.findViewById(R.id.restcontactno);
            restLocation = (TextView)itemView.findViewById(R.id.restlocation);


        }
    }

}
