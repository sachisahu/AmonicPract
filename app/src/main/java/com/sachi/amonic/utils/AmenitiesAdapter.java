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

import java.util.ArrayList;
import java.util.List;

public class AmenitiesAdapter extends ArrayAdapter {
    Activity activity;
    List<AmenitiesDataClass> amenitiesDataClasses;

    public AmenitiesAdapter(@NonNull Activity activity, List<AmenitiesDataClass> amenitiesDataClasses) {
        super(activity, R.layout.amenities_row,amenitiesDataClasses);

        this.activity = activity;
        this.amenitiesDataClasses = amenitiesDataClasses;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =  inflater.inflate(R.layout.amenities_row,null,true);

        TextView product = view.findViewById(R.id.product);
        TextView rate = view.findViewById(R.id.rate);

        AmenitiesDataClass amenitiesDataClass = (AmenitiesDataClass) getItem(position);

        product.setText(amenitiesDataClass.product);
        rate.setText(amenitiesDataClass.rate);


        return view;
    }
}
