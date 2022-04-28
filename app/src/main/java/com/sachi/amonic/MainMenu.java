package com.sachi.amonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.sachi.amonic.databinding.ActivityMainMenuBinding;
import com.sachi.amonic.utils.SP;

public class MainMenu extends AppCompatActivity {

    ActivityMainMenuBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainMenuBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.lblEmail.setText("Hy, "+SP.getSp(this,"login","user"));

        binding.btnAbout.setOnClickListener(x-> {
            Intent i = new Intent(MainMenu.this,AboutAirline.class);
            startActivity(i);
        });
        binding.btnAmenities.setOnClickListener(x->{
            Intent i = new Intent(MainMenu.this,Amenities.class);
            startActivity(i);
        });

        binding.btnSearch.setOnClickListener(c->{
            Intent i = new Intent(MainMenu.this,SearchFlight.class);
            startActivity(i);
        });
        binding.btnReserveSeat.setOnClickListener(c->{
            Intent i = new Intent(MainMenu.this,ReserveSeat.class);
            startActivity(i);
        });
    }
}