package com.example.check;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class verification extends AppCompatActivity {
    public static final String j1 = "j1";
    Button b1;
    TextInputEditText e1;
    String message,mailid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        b1 = findViewById(R.id.bt1);
        e1 = findViewById(R.id.et1);
        Intent intent = getIntent();
        message = intent.getStringExtra("j1");
        mailid=intent.getStringExtra("Mailid");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if (e1.getText().toString().matches(message)) {
                    Intent i2 = new Intent(verification.this, forgot.class);
                    i2.putExtra("Mailid",mailid);
                    startActivity(i2);
                }
            }
        });

    }
}
