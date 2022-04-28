package com.sachi.amonic.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SP {
    public static void saveSp(Activity activity,String name,String key,String value){
        SharedPreferences.Editor editor = activity.getSharedPreferences(name, Context.MODE_PRIVATE).edit();
        editor.putString(key,value);
        editor.apply();
    }
    public static String getSp(Activity activity,String name,String key){
        SharedPreferences sp = activity.getSharedPreferences(name, Context.MODE_PRIVATE);
        String value = sp.getString(key,"");
        return value;
    }

}
