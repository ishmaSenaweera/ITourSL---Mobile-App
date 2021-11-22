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


public class Hotel_descfragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    String hotelName, hotelDescription, hotelAddress, hotelContactNo, hotelLocation, hotelUrl, hotelImage, hotelUsername;


    public Hotel_descfragment() {
        // Required empty public constructor
    }

    public Hotel_descfragment(String hotelName, String hotelDescription, String hotelAddress, String hotelContactNo, String hotelLocation, String hotelUrl, String hotelImage, String hotelUsername) {

        this.hotelAddress = hotelAddress;
        this.hotelContactNo = hotelContactNo;
        this.hotelDescription = hotelDescription;
        this.hotelImage = hotelImage;
        this.hotelLocation = hotelLocation;
        this.hotelName = hotelName;
        this.hotelUrl = hotelUrl;
        this.hotelUsername = hotelUsername;

    }


    public static Hotel_descfragment newInstance(String param1, String param2) {
        Hotel_descfragment fragment = new Hotel_descfragment();
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
        View view = inflater.inflate(R.layout.fragment_hotel_descfragment, container, false);

        ImageView hotelimageholder = view.findViewById(R.id.hotelimageholder);
        TextView hotelnameholder = view.findViewById(R.id.hotelnameholder);
        TextView hoteladdressholder = view.findViewById(R.id.hoteladdressholder);
        TextView hotelemailholder = view.findViewById(R.id.hotelemailholder);
        TextView hotelcontactholder = view.findViewById(R.id.hotelcontactholder);
        TextView hotellocationholder = view.findViewById(R.id.hotellocationholder);
        TextView hotelurlholder = view.findViewById(R.id.hotelurlholder);
        TextView hoteldescholder = view.findViewById(R.id.hoteldescholder);

        hotelnameholder.setText(hotelName);
        hoteladdressholder.setText(hotelAddress);
        hotelemailholder.setText(hotelUsername);
        hotelcontactholder.setText(hotelContactNo);
        hotellocationholder.setText(hotelLocation);
        hotelurlholder.setText(hotelUrl);
        hoteldescholder.setText(hotelDescription);

        Glide.with(getContext()).load(hotelImage).into(hotelimageholder);
        return view;


    }


    public void onBackPressed () {
        AppCompatActivity activity = (AppCompatActivity) getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.Hotel_wrapper,
                new Hotel_recfragment()).addToBackStack(null).commit();
    }



}