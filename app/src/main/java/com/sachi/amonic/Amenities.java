package com.sachi.amonic;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.sachi.amonic.utils.AmenitiesAdapter;
import com.sachi.amonic.utils.AmenitiesDataClass;

import java.util.ArrayList;

public class Amenities extends AppCompatActivity {

    ListView listView;
    Activity activity;
    ArrayList<AmenitiesDataClass> amenitiesDataClasses = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amenities);
        listView = findViewById(R.id.listviewAmii);
        amenitiesDataClasses.add(new AmenitiesDataClass("Extra Blanket","10"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Next Seat Free","30"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Two Neighboring Seats Free","50"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Tablet Rental","12"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Laptop Rental","15"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Lounge Access","25"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Soft Drinks","0"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Premium Headphones Rental","5"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Extra Bag","15"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Fast Checkin Lane","10"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Wi-Fi 50 mb","0"));
        amenitiesDataClasses.add(new AmenitiesDataClass("Wi-Fi 250 mb","25"));


        activity = this;

        AmenitiesAdapter adapter = new AmenitiesAdapter(activity,amenitiesDataClasses);
        listView.setAdapter(adapter);





    }
}