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


public class recfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    TransportAdapter transportAdapter;

    public recfragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static recfragment newInstance(String param1, String param2) {
        recfragment fragment = new recfragment();
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
       View view=inflater.inflate(R.layout.fragment_recfragment, container, false);
       recyclerView =(RecyclerView)view.findViewById(R.id.transrecyclerview);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FirebaseRecyclerOptions<TransportModel> options=
                new FirebaseRecyclerOptions.Builder<TransportModel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("TransportProvider"),TransportModel.class)
                        .build();
        transportAdapter = new TransportAdapter(options);
        recyclerView.setAdapter(transportAdapter);
       return view;
    }
    public void onStart(){
        super.onStart();
        transportAdapter.startListening();
    }
    public void onStop(){
        super.onStop();
        transportAdapter .stopListening();
    }
}