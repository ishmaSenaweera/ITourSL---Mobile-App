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

public class ShopAdapter extends FirebaseRecyclerAdapter<Shop,ShopAdapter.myViewHolder>{
        /**
         * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
         * {@link FirebaseRecyclerOptions} for configuration options.
         *
         * @param options
         */
        public ShopAdapter(@NonNull FirebaseRecyclerOptions<Shop> options) {
            super(options);
        }

        @Override
        protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Shop model) {
            holder.s_name.setText(model.getS_name());
            holder.s_description.setText(model.getS_description());

            holder.s_contactno.setText(model.getS_contactno());
            holder.s_location.setText(model.getS_location());

            Glide.with(holder.img.getContext()).load(model.getS_image())
                    .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                    .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                    .into(holder.img);

            holder.img.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view) {
                    AppCompatActivity activity =(AppCompatActivity)view.getContext();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper1,new Shopdescfragment(model.getS_name(),model.getS_description(),model.getS_address(),model.getS_contactno(),model.getS_location(),model.getS_pageurl(),model.getS_username(), model.getS_image())).addToBackStack(null).commit();
                }
            });
        }

        @NonNull
        @Override
        public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_item,parent,false);
            return new myViewHolder(view);
        }

        class myViewHolder extends RecyclerView.ViewHolder{
            ImageView img;
            TextView s_name,s_description,s_contactno,s_location,email;

            public myViewHolder(@NonNull View itemView) {
                super(itemView);

                img = (ImageView) itemView.findViewById(R.id.shopr_image);
                s_name = (TextView) itemView.findViewById(R.id.shopr_name);
                s_description = (TextView) itemView.findViewById(R.id.shopr_desc);
                s_contactno = (TextView) itemView.findViewById(R.id.shopr_no);
                s_location = (TextView) itemView.findViewById(R.id.shopr_loc);

            }
        }
}
