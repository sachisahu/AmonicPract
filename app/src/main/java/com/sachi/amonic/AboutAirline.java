package com.sachi.amonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.badge.BadgeUtils;

public class AboutAirline extends AppCompatActivity {

    Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_airline);
        back = findViewById(R.id.btnBack);
        back.setOnClickListener(x-> {
            Intent i = new Intent(AboutAirline.this,MainMenu.class);
            startActivity(i);
        });
    }
}