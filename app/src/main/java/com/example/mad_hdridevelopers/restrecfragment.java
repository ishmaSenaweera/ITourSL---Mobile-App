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


public class restrecfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    RestAdapter restAdapter;

    public restrecfragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static restrecfragment newInstance(String param1, String param2) {
        restrecfragment fragment = new restrecfragment();
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
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_restrecfragment, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.restrecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<Restaurant> options=
                new FirebaseRecyclerOptions.Builder<Restaurant>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Restaurant"),Restaurant.class)
                        .build();
        restAdapter = new RestAdapter(options);
        recyclerView.setAdapter(restAdapter);
        return view;
    }
    public void onStart(){
        super.onStart();
        restAdapter.startListening();
    }
    public void onStop(){
        super.onStop();
        restAdapter .stopListening();
    }
}
