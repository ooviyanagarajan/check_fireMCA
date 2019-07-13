package com.example.check;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

public class notifypeople extends AppCompatActivity {
    ImageView img;
    Button b1;
    EditText et1;
    String inform;
    Bitmap bitmap;
    String name,email;
    private static final int REQUEST_CODE_PERMISSION = 2;
    String mPermission = Manifest.permission.ACCESS_FINE_LOCATION;
    public static PackageManager MockPackageManager  ;

    // GPSTracker class
    GPSTracker gps;
    double latitude;
    double longitude;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifypeople);
        try {
            if (ActivityCompat.checkSelfPermission(this, mPermission)!= MockPackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this, new String[]{mPermission},
                        REQUEST_CODE_PERMISSION);

                // If any permission above not allowed by user, this condition will
                //   execute every time, else your else part will work
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        img=findViewById(R.id.iv1);
        b1=findViewById(R.id.bt1);
        et1=findViewById(R.id.ed1);
        SharedPreferences sharedpreferences = getSharedPreferences("userdetail", Context.MODE_PRIVATE);
        name=sharedpreferences.getString("NAME","can't get");
        email=sharedpreferences.getString("EMAIL","can't get");
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                startActivityForResult(intent,7);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gps = new GPSTracker(notifypeople.this);

                // check if GPS enabled
                if(gps.canGetLocation()){

                    latitude = gps.getLatitude();
                    longitude = gps.getLongitude();

                    // \n is for new line
                    }else{
                    // can't get location
                    // GPS or Network is not enabled
                    // Ask user to enable GPS/network in settings
                    gps.showSettingsAlert();
                }

                validate();
                inform=et1.getText().toString();

            }
        });

    }
     private void validate()
    {
        inform = et1.getText().toString();
        if(validateinfo()){
            signup();
            Toast.makeText(getApplicationContext(),"sending",Toast.LENGTH_SHORT).show();
        }
    }
    private boolean validateinfo(){
        if(inform.isEmpty()){
            et1.setError("enter the info:");
            return false;
        }
        else
        {
            return true;
        }

    }
    private void signup()
    {
        inform=et1.getText().toString();
        StringRequest sr=new StringRequest(Request.Method.POST, "https://ooviyaarn.000webhostapp.com/fire/firephp/firephp/imagenoti.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("k5",imageTostring(bitmap));
                params.put("k6", et1.getText().toString());
                params.put("k1",name);
                params.put("k2",email);
                params.put("k3", String.valueOf(latitude));
                params.put("k4", String.valueOf(longitude));

                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(notifypeople.this);
        requestQueue.add(sr);


    }

    public String imageTostring(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] imaBytes = byteArrayOutputStream.toByteArray();
        return Base64.encodeToString(imaBytes, Base64.DEFAULT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,Intent data) {
        if (requestCode == 7 && resultCode == RESULT_OK) {
            bitmap = (Bitmap) data.getExtras().get("data");
            img.setImageBitmap(bitmap);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}