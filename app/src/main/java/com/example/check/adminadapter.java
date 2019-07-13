package com.example.check;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class adminadapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<adminproduct> newsList;

    public adminadapter(Context applicationContext, List<adminproduct> recyproducts) {
        this.context = applicationContext;
        this.newsList= recyproducts;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row=inflater.inflate(R.layout.admindesign,viewGroup,false);
        Item item=new Item(row);
        return item;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final adminproduct newsproduct = newsList.get(i);


        ((Item)viewHolder).name.setText(newsproduct.getName());
        ((Item)viewHolder).email.setText(newsproduct.getEmail());
        Picasso.get().load(newsproduct.getImageurl()).into(((Item)viewHolder).imageurl);
        ((Item)viewHolder).information.setText(newsproduct.getInformation());
        ((Item)viewHolder).date_time.setText(newsproduct.getDate_time());

        ((Item)viewHolder).admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,Adminmap.class);
                i.putExtra("LAT",newsproduct.getLatitude());
                i.putExtra("LNG",newsproduct.getLongitude());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(i);
                }
        });


    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public class Item extends RecyclerView.ViewHolder {
        TextView name,email,information,date_time;
        ImageView imageurl;
        CardView admin;

        public Item(View itemView) {
            super(itemView);
            admin=itemView.findViewById(R.id.admincard);
            name= itemView.findViewById(R.id.aname);
            email = itemView.findViewById(R.id.aemail);
            imageurl=itemView.findViewById(R.id.aimageurl);
            information=itemView.findViewById(R.id.ainformation);
            date_time=itemView.findViewById(R.id.adate_time);

        }

    }


}


