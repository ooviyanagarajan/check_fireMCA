package com.example.check;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import static android.widget.Toast.LENGTH_SHORT;

public class forgot extends AppCompatActivity {
    TextInputEditText e1,e2;
    String newpassword,confirmpassword,emailides;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot);
        e1=findViewById(R.id.passwordn);
        e2=findViewById(R.id.passwordc);
        b=findViewById(R.id.update);
        Bundle data=getIntent().getExtras();
        emailides=data.getString("Mailid");
        newpassword=e1.getText().toString();
        confirmpassword=e2.getText().toString();
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(newpassword.equals(confirmpassword))
                {
                    passwordupdationdetails();
                   // Intent i=new Intent(forgot.this,login.class);
                    //startActivity(i);
                }
                else
                    Toast.makeText(getApplicationContext(),"PasswordsMismatch",LENGTH_SHORT).show();

            }
        });
    }
    private void passwordupdationdetails()
    {
        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/pwupdate.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(),response,Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), "updated details", LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),LENGTH_SHORT).show();
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("MAILID",emailides);
                params.put("PASSWORD",e1.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);

    }
}