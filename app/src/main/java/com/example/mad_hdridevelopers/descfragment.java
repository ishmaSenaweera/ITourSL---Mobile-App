package com.example.mad_hdridevelopers;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


public class descfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String name,description,address,contactno,location,url,email,image;
    public descfragment() {

    }
    public descfragment(String name, String description, String address, String contactno,String location,String url,String email,String image) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.contactno = contactno;
        this.location = location;
        this.url = url;
        this.email = email;
        this.image = image;
    }


    // TODO: Rename and change types and number of parameters
    public static descfragment newInstance(String param1, String param2) {
        descfragment fragment = new descfragment();
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

        View view= inflater.inflate(R.layout.fragment_descfragment, container, false);
        ImageView imageholder=view.findViewById(R.id.imageholder);
        TextView nameholder=view.findViewById(R.id.nameholder);
        TextView descholder=view.findViewById(R.id.descholder);
        TextView addressholder=view.findViewById(R.id.addressholder);
        TextView contactholder=view.findViewById(R.id.contactholder);
        TextView locationholder=view.findViewById(R.id.locationholder);
        TextView emailholder=view.findViewById(R.id.emailholder);

        nameholder.setText(name);
        descholder.setText(description);
        addressholder.setText(address);
        contactholder.setText(contactno);
        locationholder.setText(location);
        emailholder.setText(email);

        Glide.with(getContext()).load(image).into(imageholder);

        return view;
    }
    public void onBackPressed(){
        AppCompatActivity activity =(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new recfragment()).addToBackStack(null).commit();
    }
}