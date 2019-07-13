package com.example.check;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextInputEditText ed1, ed2, ed3, ed4, ed5, ed6;
    TextView v1;
    Button bt1;
    int i2;
    String I,I2,nm8;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ed1 = findViewById(R.id.et1);
        ed2 = findViewById(R.id.et2);
        ed3 = findViewById(R.id.et3);
        ed4 = findViewById(R.id.et4);
        ed5 = findViewById(R.id.et5);
        ed6 = findViewById(R.id.et6);

        bt1 = findViewById(R.id.bt1);
        v1 = findViewById(R.id.tv1);
        v1.setOnClickListener(new View.OnClickListener() {
                                  @Override
                                  public void onClick(View v) {
                                      Intent v1 = new Intent(MainActivity.this, login.class);
                                      startActivity(v1);
                                  }
                              });
                bt1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                /*Intent intent = new Intent(RegistrationActivity.this,view.class);
                startActivity(intent);*/
                        String s = ed1.getText().toString().trim();
                        String s2 = ed2.getText().toString().trim();
                        String s3 = ed3.getText().toString().trim();
                        String s4 = ed4.getText().toString().trim();
                        String s5 = ed5.getText().toString().trim();
                        String s6 = ed6.getText().toString().trim();



                        if (ed1.getText().toString().isEmpty()) {
                            ed1.setError("enter name");
                        } else if (ed2.getText().toString().isEmpty()) {
                            ed2.setError("enter enterpassword");

                        } else if (ed3.getText().toString().isEmpty()) {
                            ed3.setError("re-enter password");

                        }  else if (ed4.getText().toString().isEmpty()) {
                            ed4.setError("enter city");

                        } else if (ed5.getText().toString().isEmpty()) {
                            ed5.setError("enter phone number");

                        }
                         else if (ed6.getText().toString().isEmpty()) {
                            ed6.setError("enter email");

                        }  else if (!ed3.getText().toString().equals(ed2.getText().toString())) {

                            ed3.setError("re enter same password:");
                        } else {


                            Toast.makeText(getApplicationContext(), s + s2 + s3 + s4 + s5 + s6, Toast.LENGTH_SHORT).show();

                            final String nm = ed1.getText().toString();
                            final String nm2 = ed2.getText().toString();
                            final String nm3 = ed4.getText().toString();
                            final String nm4 = ed5.getText().toString();
                            final String nm5 = ed6.getText().toString();


                           // Random rand = new Random();
                           // i2 = rand.nextInt(10000);
                           // I = Integer.toString(i2);


                            StringRequest sr = new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/register.php", new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    if (response.equals("success")) {
                                        Intent intent=new Intent(MainActivity.this,login.class);
                                        startActivity(intent);
                                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "existing Email id", Toast.LENGTH_LONG).show();
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
                                    params.put("k1", nm);
                                    params.put("k2", nm2);
                                    params.put("k3", nm3);
                                    params.put("k4", nm4);
                                    params.put("k5", nm5);
                                    return params;


                                }
                            };
                            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
                            requestQueue.add(sr);
                        }
                    }
                });

            }
        }


