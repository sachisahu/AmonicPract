package com.sachi.amonic.utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.sachi.amonic.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FlightListAdapter extends ArrayAdapter {

    Activity activity;
    List<FlightDatailsDataClass> listFlight;
    String selecteddate;

    public FlightListAdapter(@NonNull Activity activity, List<FlightDatailsDataClass> listFlight,String date) {
        super(activity, R.layout.flight_details_row,listFlight);
        this.activity = activity;
        this.listFlight = listFlight;
        this.selecteddate = date;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.flight_details_row,null,true);
        TextView flightNo = view.findViewById(R.id.flightNo);
        TextView price = view.findViewById(R.id.price);
        TextView time = view.findViewById(R.id.time2);
        TextView aricraft = view.findViewById(R.id.aircraft);
        TextView date = view.findViewById(R.id.date);

        FlightDatailsDataClass flightDataClass = (FlightDatailsDataClass) getItem(position);

        flightNo.setText(flightDataClass.flightNo);
        price.setText(flightDataClass.price);
        time.setText(flightDataClass.time);
        aricraft.setText(flightDataClass.aircraft);
        date.setText(selecteddate+"");



        return view;
    }
}
