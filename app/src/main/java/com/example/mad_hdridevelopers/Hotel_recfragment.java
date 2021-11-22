package com.example.mad_hdridevelopers;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;


public class Hotel_recfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    RecyclerView Hotel_recview;
    HotelAdapter hotelAdapter;

    public Hotel_recfragment() {
        // Required empty public constructor
    }


    public static Hotel_recfragment newInstance(String param1, String param2) {
        Hotel_recfragment fragment = new Hotel_recfragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hotel_recfragment, container, false);
        Hotel_recview = (RecyclerView) view.findViewById(R.id.Hotel_recview);
        Hotel_recview.setLayoutManager(new LinearLayoutManager(getContext()));

        FirebaseRecyclerOptions<Hotel> options =
                new FirebaseRecyclerOptions.Builder<Hotel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Hotel"), Hotel.class)
                        .build();

                hotelAdapter = new HotelAdapter(options);
                Hotel_recview.setAdapter(hotelAdapter);

        return view;

    }

    public void onStart(){
        super.onStart();
        hotelAdapter.startListening();
    }
    public void onStop(){
        super.onStop();
        hotelAdapter.stopListening();
    }


}