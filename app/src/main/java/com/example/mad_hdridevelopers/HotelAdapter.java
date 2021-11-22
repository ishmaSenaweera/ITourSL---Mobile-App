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

public class HotelAdapter extends FirebaseRecyclerAdapter<Hotel, HotelAdapter.myviewholder> {



    public HotelAdapter(@NonNull FirebaseRecyclerOptions<Hotel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Hotel model) {
        holder.hotelrecyname.setText(model.getHotelName());
        holder.hotelrecydesc.setText(model.getHotelDescription());
        holder.hotelrecylocation.setText(model.getHotelLocation());
        holder.hotelrecycontactno.setText(model.getHotelContactNo());
        Glide.with(holder.hotelrecyimage1.getContext()).load(model.getHotelImage()).into(holder.hotelrecyimage1);

        holder.hotelrecyimage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity)view.getContext();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.Hotel_wrapper,
                        new Hotel_descfragment(model.getHotelName(),model.getHotelDescription(),model.getHotelAddress(),model.getHotelContactNo(),model.getHotelLocation(),model.getHotelUrl(),model.getHotelImage(),model.getHotelUsername())).addToBackStack(null).commit();
            }
        });


    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hotel_item,parent,false);
        return new myviewholder(view);

    }

    public class myviewholder extends RecyclerView.ViewHolder{

    ImageView hotelrecyimage1;
    TextView hotelrecyname,hotelrecydesc,hotelrecylocation,hotelrecycontactno;

        public myviewholder(@NonNull View itemView) {
            super(itemView);

            hotelrecyimage1 = itemView.findViewById(R.id.hotelrecyimage1);
            hotelrecyname = itemView.findViewById(R.id.hotelrecyname);
            hotelrecydesc = itemView.findViewById(R.id.hotelrecydesc);
            hotelrecylocation = itemView.findViewById(R.id.hotelrecylocation);
            hotelrecycontactno = itemView.findViewById(R.id.hotelrecycontactno);
        }
    }

}
