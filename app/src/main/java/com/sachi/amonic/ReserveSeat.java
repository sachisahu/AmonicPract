package com.sachi.amonic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sachi.amonic.databinding.ActivityReserveSeatBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ReserveSeat extends AppCompatActivity {

    ActivityReserveSeatBinding binding;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityReserveSeatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.ButtonNext.setOnClickListener(c-> {
            if(binding.EditTicket.getText().toString().length()>0){
                loadImg(binding.EditTicket.getText().toString());
                binding.EditTicket.setText("");
                count = 0;

            }
            else {
                Toast.makeText(this, "Enter Ticket No", Toast.LENGTH_SHORT).show();
            }
        });

        binding.seatTypeImage.setOnClickListener(h->{
            count++;
        });

        binding.btnReserve.setOnClickListener(h->{
            if(count>=1){
                Toast.makeText(this, "Booked", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "Select A Seat", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadImg(String s) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/api/ticket/"+s)
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Api tkt","failed"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("Api tkt","Success");
                String seatType;
                String data = response.body().string();
                try {

                    JSONObject object = new JSONObject(data);
                    seatType = object.getString("type");
                    Log.d("Tkt Type","Tkt Type"+seatType);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if(seatType.contains("business")){
                                binding.seatTypeImage.setImageDrawable(getDrawable(R.drawable.b));
                            }
                            if(seatType.contains("first")){
                                binding.seatTypeImage.setImageDrawable(getDrawable(R.drawable.f));
                            }
                            if(seatType.length()<=0){
                                Toast.makeText(ReserveSeat.this, "Tkt Not Found", Toast.LENGTH_SHORT).show();
                            }
                            if(seatType.contains("economy")){
                                Toast.makeText(ReserveSeat.this, "Economy Tkt", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}