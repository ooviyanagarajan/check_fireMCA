package com.example.check;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class adminwelcome extends AppCompatActivity {
    RecyclerView recyclerView;
    List<adminproduct> recyproducts;
    adminadapter adapter;
    View v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminwelcome);
        recyclerView=findViewById(R.id.arecycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyproducts = new ArrayList<>();
        adapter = new adminadapter(getApplicationContext(), recyproducts);
        recyclerView.setAdapter(adapter);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/viewnoti.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //converting the string to json array object
                    Toast.makeText(getApplicationContext(),response,Toast.LENGTH_LONG).show();

                    JSONObject obj = new JSONObject(response);
                    JSONArray arr = obj.getJSONArray("project_details");//json array formate name in php

                    //traversing through all the object
                    for (int i = 0; i < arr.length(); i++) {

                        //getting product object from json array
                        JSONObject newsmessage = arr.getJSONObject(i);

                        //adding the product to product list);
                        recyproducts.add(new adminproduct(newsmessage.getString("name"), newsmessage.getString("email"),newsmessage.getString("latitude"),newsmessage.getString("longitude"),newsmessage.getString("imageurl"),newsmessage.getString("information"),newsmessage.getString("date_time")));


                    }


                    //creating adapter object and setting it to recyclerview

                } catch (JSONException e) {
                    e.printStackTrace();
                }


                adapter.notifyDataSetChanged();
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG).show();

            }
        }
        ){

        };

        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}




