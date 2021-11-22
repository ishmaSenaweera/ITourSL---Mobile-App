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

public class TransportAdapter extends FirebaseRecyclerAdapter<TransportModel,TransportAdapter.myViewHolder>{
    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public TransportAdapter(@NonNull FirebaseRecyclerOptions<TransportModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull TransportModel model) {
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());

        holder.contactno.setText(model.getContactno());
        holder.location.setText(model.getLocation());

        Glide.with(holder.img.getContext()).load(model.getImage())
                .placeholder(R.drawable.common_google_signin_btn_icon_dark)
                .error(R.drawable.common_google_signin_btn_icon_dark_normal)
                .into(holder.img);

          holder.img.setOnClickListener(new View.OnClickListener(){
              @Override
              public void onClick(View view) {
                  AppCompatActivity activity =(AppCompatActivity)view.getContext();
                  activity.getSupportFragmentManager().beginTransaction().replace(R.id.wrapper,new descfragment(model.getName(),model.getDescription(),model.getAddress(),model.getContactno(),model.getLocation(),model.getUrl(),model.getEmail(), model.getImage())).addToBackStack(null).commit();
              }
          });
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transport_item,parent,false);
        return new myViewHolder(view);
    }

    class myViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView name,description,contactno,location,email;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            img = (ImageView)itemView.findViewById(R.id.recycleimage1);
            name = (TextView)itemView.findViewById(R.id.rname);
            description = (TextView)itemView.findViewById(R.id.rdesc);
            contactno = (TextView)itemView.findViewById(R.id.rcontactno);
            location = (TextView)itemView.findViewById(R.id.rlocation);


        }
    }

}
