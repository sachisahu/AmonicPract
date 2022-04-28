package com.sachi.amonic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import com.sachi.amonic.databinding.ActivityMainBinding;
import com.sachi.amonic.utils.SP;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        if(SP.getSp(this,"login","user").length()>0){
            Intent i = new Intent(MainActivity.this,MainMenu.class);
            startActivity(i);
            finish();
        }

        binding.loginButton.setOnClickListener(x-> {
            if(binding.emailEditText.getText().length()>0){
                String value = binding.emailEditText.getText().toString();
                SP.saveSp(this,"login","user",value);


                Intent i = new Intent(MainActivity.this,MainMenu.class);
                startActivity(i);
            }else {
                Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
            }
        });
    }
}