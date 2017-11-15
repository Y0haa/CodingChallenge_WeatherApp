package com.example.admin.codingchallenge_weatherapp.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Admin on 11/14/2017.
 */

public class LastLocation {

    private static final String TAG = "MainActivity";
    public static final String MY_PREF_FILE = "com.example.admin.codingchallenge_weatherapp";

    Context context;
    // creating a context for the data
    public LastLocation( Context context) {

        this.context = context;

    }
    // saving the location from the last request
    public void SaveCity (String city) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(MY_PREF_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        try {
            // String s = "string";
            //Log.d(TAG, "onHandleIntent: " + s);
            editor.putString("lastCityKey",city);
            editor.commit();

        } catch (Exception e) {
            Log.d(TAG, "sharepref error: " + e.toString());
        }

    }





}
