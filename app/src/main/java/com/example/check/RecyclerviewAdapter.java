package com.example.check;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<Recycproduct> newsList;

    public RecyclerviewAdapter(Context applicationContext, List<Recycproduct> recyproducts) {
        this.context = applicationContext;
        this.newsList= recyproducts;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View row=inflater.inflate(R.layout.membersdesign,viewGroup,false);
        Item item=new Item(row);
        return item;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        Recycproduct newsproduct = newsList.get(i);

        ((Item)viewHolder).name.setText(newsproduct.getName());
        ((Item)viewHolder).email.setText(newsproduct.getEmail());
        ((Item)viewHolder).area.setText(newsproduct.getArea());
        ((Item)viewHolder).phno.setText(newsproduct.getPhno());

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }
    public class Item extends RecyclerView.ViewHolder {
        TextView name,email,area,phno;



        public Item(View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.mname);
            email = itemView.findViewById(R.id.memail);
            area= itemView.findViewById(R.id.marea);
            phno=itemView.findViewById(R.id.mphno);
        }

    }


}

