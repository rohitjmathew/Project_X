package com.dev.rohitmathew.crypto;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONArray;
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
            JSONArray prods = dat.getJSONArray("logs");
            if(prods != null){
                for(int j = 0; j < prods.length();j++){
                    JSONObject innerElem = prods.getJSONObject(j);
                    if(innerElem != null){
                        boolean inv_width = innerElem.getBoolean("inv_width");
                        if(inv_width)
                        {
                            TextView bit = (TextView) findViewById(R.id.info_text3);
                            bit.setText("฿ "+ (10-btc));
                        }
                    }
                }
            }
        }
        catch(Exception e){}
    }

}
