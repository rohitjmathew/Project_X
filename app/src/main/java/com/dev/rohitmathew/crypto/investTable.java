package com.dev.rohitmathew.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

public class investTable extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_table);
        sharedPreferences= getApplicationContext().getSharedPreferences("text", Context.MODE_PRIVATE);
        String response=sharedPreferences.getString("table"," ");

        try {
            JSONObject person = new JSONObject(response);
            JSONObject dat = person.getJSONObject("data");
            Log.e("tab",dat.getString("logs"));
        }
        catch(Exception e){}
    }
}
