package com.example.check;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class welcome extends AppCompatActivity {
    ImageView iv1,iv2,iv3,iv4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
     iv1=findViewById(R.id.ntp);
     iv2=findViewById(R.id.call);
     iv3=findViewById(R.id.list);
     iv4=findViewById(R.id.notificationlist);
     iv1.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i = new Intent(welcome.this,notifypeople.class);
             startActivity(i);
         }
     });
     iv2.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             Intent i= new Intent(welcome.this,map.class);
             startActivity(i);
         }
     });
        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(welcome.this,view.class);
                startActivity(i);
            }
        });
        iv4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(welcome.this,noti_view.class);
                startActivity(i);
            }
        });
    }
}
