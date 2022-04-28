package com.sachi.amonic;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.Toolbar;

import com.sachi.amonic.databinding.ActivitySearchFlightBinding;
import com.sachi.amonic.utils.AirportDataClass;
import com.sachi.amonic.utils.FlightDatailsDataClass;
import com.sachi.amonic.utils.FlightListAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchFlight extends AppCompatActivity {

    ActivitySearchFlightBinding binding;
    List<AirportDataClass> listAirpirt = new ArrayList<>();
    List<FlightDatailsDataClass> listFlightDetails = new ArrayList<>();
    String fromID,toID;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchFlightBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        activity = this;

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int date = calendar.get(Calendar.DAY_OF_MONTH);

        binding.edtDate.setOnClickListener(o->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(SearchFlight.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                    binding.edtDate.setText(i + "/" + (i1 + 1) + "/" + i2);
                }
            },year,month,date);
            datePickerDialog.show();
        });

        loadDataSpinner();

        binding.SpinnerFrom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AirportDataClass airClass = (AirportDataClass) binding.SpinnerFrom.getSelectedItem();
                Log.d("SpinnerSelected",airClass.airport+" %% " + airClass.id);
                fromID = airClass.id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.SpinnerTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                AirportDataClass airClass = (AirportDataClass) binding.SpinnerTo.getSelectedItem();
                toID = airClass.id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.btnSearch.setOnClickListener(h->{
            if(Integer.parseInt(fromID) != Integer.parseInt(toID)){
                if(binding.edtDate.getText().length()>0){
                    String url = "http://10.0.2.2:5000/api/schedule/list?from="+fromID+"&to="+toID+"&date="+binding.edtDate.getText().toString()+"";
                    Log.d("URL",url+"");
                    loadDataList(url);
                }
                else {
                    Toast.makeText(this, "Select Date", Toast.LENGTH_SHORT).show();
                }
            }else {
                Toast.makeText(this, "From And To Cannot Be Same", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void loadDataList(String url) {

        listFlightDetails.clear();
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(url)
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
            String data = response.body().string();
            Log.d("Data",data+"");
                try {
                    JSONArray array = new JSONArray(data);
                    for(int x=0;x< array.length();x++){
                        JSONObject object = array.getJSONObject(x);
                        String flightNo = object.getString("flightNumber");
                        String price = object.getString("price");
                        String time = object.getString("time");
                        String aircraft = object.getString("aircraft");

                        listFlightDetails.add(new FlightDatailsDataClass(flightNo,price,time,aircraft));

                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            FlightListAdapter adapter = new FlightListAdapter(activity,listFlightDetails,binding.edtDate.getText().toString());
                            binding.ListViews.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //Dataload spinner
    private void loadDataSpinner() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("http://10.0.2.2:5000/api/port/list")
                .method("GET", null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("Api Con","Failed"+e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                Log.d("Api Con","Success");
                String data = response.body().string();
                try {
                    JSONArray array = new JSONArray(data);

                    for(int x=0;x< array.length();x++){
                        JSONObject object = array.getJSONObject(x);
                        String id = object.getString("id");
                        String airport = object.getString("name");
                        Log.d("Data : ","ID = "+id+", Airport = "+airport);
                        listAirpirt.add(new AirportDataClass(id,airport));
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ArrayAdapter adapter = new ArrayAdapter(SearchFlight.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,listAirpirt);
                            binding.SpinnerFrom.setAdapter(adapter);
                            binding.SpinnerTo.setAdapter(adapter);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}