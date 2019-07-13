package com.example.check;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

    public class viewproductAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
        Context context;
        List<viewproduct> newsList;

        public viewproductAdapter(Context applicationContext, List<viewproduct> recyproducts) {
            this.context = applicationContext;
            this.newsList= recyproducts;
        }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            LayoutInflater inflater=LayoutInflater.from(context);
            View row=inflater.inflate(R.layout.notificationlist,viewGroup,false);
            Item item=new Item(row);
            return item;


        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

            viewproduct newsproduct = newsList.get(i);

            ((Item)viewHolder).name.setText(newsproduct.getName());
            ((Item)viewHolder).email.setText(newsproduct.getEmail());
            ((Item)viewHolder).latitude.setText(newsproduct.getLatitude());
            ((Item)viewHolder).longitude.setText(newsproduct.getLongitude());
            //((Item)viewHolder).imageurl.setText(newsproduct.getImageurl());

           Picasso.get().load(newsproduct.getImageurl()).into(((Item)viewHolder).imageurl);
            ((Item)viewHolder).information.setText(newsproduct.getInformation());
            ((Item)viewHolder).date_time.setText(newsproduct.getDate_time());





        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
        public class Item extends RecyclerView.ViewHolder {
            TextView name,email,latitude,longitude,information,date_time;
ImageView imageurl;


            public Item(View itemView) {
                super(itemView);

                name= itemView.findViewById(R.id.vname);
                email = itemView.findViewById(R.id.vemail);
                latitude= itemView.findViewById(R.id.vlatitude);
                longitude=itemView.findViewById(R.id.vlongitude);
                imageurl=itemView.findViewById(R.id.vimageurl);
                information=itemView.findViewById(R.id.vinformation);
                date_time=itemView.findViewById(R.id.vdate_time);

            }

        }


    }



