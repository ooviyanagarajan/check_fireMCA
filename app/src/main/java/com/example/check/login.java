package com.example.check;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class login extends AppCompatActivity {
Button bt2;
TextInputEditText e1,e2;
TextView tv1,tv2;
String I,n1;
int i2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        bt2 = findViewById(R.id.b1);
        e1= findViewById(R.id.et2);
        e2= findViewById(R.id.et3);
        tv1 = findViewById(R.id.t1);
        tv2=findViewById(R.id.t2);
        Random rand = new Random();
        i2 = rand.nextInt(10000);
        I = Integer.toString(i2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e1.getText().toString().isEmpty()) {
                    e1.setError("enter registerno");

                } else if (e2.getText().toString().isEmpty()) {
                    e2.setError("enter password");
                }
                else {
                    insertdata();
                }

    }

    private void insertdata(){
        final String n1=e1.getText().toString();
        final String n2=e2.getText().toString();


        StringRequest sr = new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/insertdata.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("failure")) {
                    if(e1.getText().toString().equals("admin") && e2.getText().toString().equals("admin"))
                    {
                        token();
                        Toast.makeText(getApplicationContext(), "Login Successfull", Toast.LENGTH_LONG).show();
                        Intent i=new Intent(getApplicationContext(),adminwelcome.class);
                        startActivity(i);

                    }
                    else
                    {
                        Toast.makeText(login.this, "invalid user", Toast.LENGTH_SHORT).show();

                    }


                   } else {
                    Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                    try {
                        token();
                        //converting the string to json array object
                        //Toast.makeText(getContext(),response,Toast.LENGTH_LONG).show();

                        JSONObject obj = new JSONObject(response);
                        JSONArray arr = obj.getJSONArray("project_details");//json array formate name in php

                        //traversing through all the object
                        for (int i = 0; i < arr.length(); i++) {

                            JSONObject newsmessage = arr.getJSONObject(i);

                            String name = newsmessage.getString("name");
                            String email = newsmessage.getString("email");
                            SharedPreferences sharedpreferences = getSharedPreferences("userdetail", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedpreferences.edit();
                            editor.putString("NAME", name);
                            editor.putString("EMAIL", email);

                            editor.commit();


                        }

                        Intent i = new Intent(login.this, welcome.class);
                        startActivity(i);




                    } catch (JSONException e){
                        e.printStackTrace();


                    }

                }

            }
            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("k1", n1);
                params.put("k2", n2);

                return params;


            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
        requestQueue.add(sr);
    }
        });

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this,MainActivity.class);
                startActivity(i);
            }
        });
tv2.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        StringRequest sr3 = new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/email2.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                Toast.makeText(login.this, "check your email for otp", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("OTP", I);
                params.put("MAILID",e1.getText().toString());
                return params;

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
        requestQueue.add(sr3);
        Intent i = new Intent(login.this, verification.class);
        i.putExtra("j1", I);
        i.putExtra("Mailid",e1.getText().toString());
        startActivity(i);
    }
});
    }

    private void token() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("FCM_PREF", Context.MODE_PRIVATE);
        final String token = sharedPreferences.getString("FCM_TOKEN", "Can't get Token");
        StringRequest sr3 = new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/noti.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),token,Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(login.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("k2",token);
                return params;

            }

        };
        RequestQueue requestQueue = Volley.newRequestQueue(login.this);
        requestQueue.add(sr3);

    }


}