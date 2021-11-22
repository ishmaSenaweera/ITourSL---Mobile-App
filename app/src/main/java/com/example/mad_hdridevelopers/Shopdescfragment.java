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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Shopdescfragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Shopdescfragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    String s_name,s_description,s_address,s_contactno,s_location,s_pageurl,s_username,s_image;

    public Shopdescfragment() {
        // Required empty public constructor
    }

    public Shopdescfragment(String s_name, String s_description, String s_address, String s_contactno, String s_location, String s_pageurl, String s_username, String s_image) {
        this.s_name = s_name;
        this.s_description = s_description;
        this.s_address = s_address;
        this.s_contactno = s_contactno;
        this.s_location = s_location;
        this.s_pageurl = s_pageurl;
        this.s_username = s_username;
        this.s_image = s_image;
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Shopdescfragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Shopdescfragment newInstance(String param1, String param2) {
        Shopdescfragment fragment = new Shopdescfragment();
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
        View view = inflater.inflate(R.layout.fragment_shopdescfragment, container, false);

        ImageView shopimageholder=view.findViewById(R.id.shopimageholder);
        TextView shopnameholde=view.findViewById(R.id.shopnameholder);
        TextView shopdescholder=view.findViewById(R.id.shopdescholder);
        TextView shopaddressholder=view.findViewById(R.id.shopaddressholder);
        TextView shopcontactholder=view.findViewById(R.id.shopcontactholder);
        TextView shoplocationholder=view.findViewById(R.id.shoplocationholder);
        TextView shopemailholder=view.findViewById(R.id.shopemailholder);

        shopnameholde.setText(s_name);
        shopdescholder.setText(s_description);
        shopaddressholder.setText(s_address);
        shopcontactholder.setText(s_contactno);
        shoplocationholder.setText(s_location);
        shopemailholder.setText(s_username);

        Glide.with(getContext()).load(s_image).into(shopimageholder);

        return view;
    }
    public void onBackPressed(){
        AppCompatActivity activity =(AppCompatActivity)getContext();
        activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper1,new Shoprecfragment()).addToBackStack(null).commit();
    }
}