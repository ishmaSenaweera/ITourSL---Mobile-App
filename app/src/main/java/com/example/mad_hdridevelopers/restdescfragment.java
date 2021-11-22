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


public class restdescfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String restName,restDescription,restAddress,restContactNo,restLocation,restUrl,restEmail,restImage;
    public restdescfragment() {
        // Required empty public constructor
    }
    public restdescfragment(String restName, String restDescription, String restAddress, String restContactNo,String restLocation,String restUrl,String restEmail,String restImage) {
        this.restName = restName;
        this.restDescription = restDescription;
        this.restAddress = restAddress;
        this.restContactNo = restContactNo;
        this.restLocation = restLocation;
        this.restUrl = restUrl;
        this.restImage = restImage;
        this.restEmail = restEmail;

    }

    // TODO: Rename and change types and number of parameters
    public static restdescfragment newInstance(String param1, String param2) {
        restdescfragment fragment = new restdescfragment();
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
        View view= inflater.inflate(R.layout.fragment_restdescfragment, container, false);
        ImageView restimageholder=view.findViewById(R.id.restimageholder);
        TextView restnameholder=view.findViewById(R.id.restnameholder);
        TextView restdescholder=view.findViewById(R.id.restdescholder);
        TextView restaddressholder=view.findViewById(R.id.restaddressholder);
        TextView restcontactholder=view.findViewById(R.id.restcontactholder);
        TextView restlocationholder=view.findViewById(R.id.restlocationholder);
        TextView restemailholder=view.findViewById(R.id.restemailholder);

        restnameholder.setText(restName);
        restdescholder.setText(restDescription);
        restaddressholder.setText(restAddress);
        restcontactholder.setText(restContactNo);
        restlocationholder.setText(restLocation);
        restemailholder.setText(restEmail);

        Glide.with(getContext()).load(restImage).into(restimageholder);

        return view;
    }
    public void onBackPressed(){
        AppCompatActivity activity =(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.rest_wrapper,new restrecfragment()).addToBackStack(null).commit();
    }
}
