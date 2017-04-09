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

import java.util.StringTokenizer;

public class investTable extends AppCompatActivity {
    SharedPreferences sharedPreferences;
    int inr,btc;
    String date;
    boolean t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invest_table);
        sharedPreferences= getApplicationContext().getSharedPreferences("text", Context.MODE_PRIVATE);
        String response=sharedPreferences.getString("table"," ");

        TextView t1 = (TextView) findViewById(R.id.t1);
        TextView t2 = (TextView) findViewById(R.id.t2);
        TextView t3 = (TextView) findViewById(R.id.t3);
        TextView t4 = (TextView) findViewById(R.id.t4);
        TextView t5 = (TextView) findViewById(R.id.t5);
        TextView t6 = (TextView) findViewById(R.id.t6);
        TextView t7 = (TextView) findViewById(R.id.t7);
        TextView t8 = (TextView) findViewById(R.id.t8);
        TextView t9 = (TextView) findViewById(R.id.t9);
        TextView t10 = (TextView) findViewById(R.id.t10);
        TextView t11 = (TextView) findViewById(R.id.t11);
        TextView t12 = (TextView) findViewById(R.id.t12);
        TextView t13 = (TextView) findViewById(R.id.t13);
        TextView t14 = (TextView) findViewById(R.id.t14);
        TextView t15 = (TextView) findViewById(R.id.t15);
        TextView t16 = (TextView) findViewById(R.id.t16);
        TextView t17 = (TextView) findViewById(R.id.t17);
        TextView t18 = (TextView) findViewById(R.id.t18);
        TextView t19 = (TextView) findViewById(R.id.t19);
        TextView t20 = (TextView) findViewById(R.id.t20);
        try {
            JSONObject person = new JSONObject(response);
            JSONObject dat = person.getJSONObject("data");
            Log.e("tab",dat.getString("logs"));
            JSONArray prods = new JSONArray(dat.getString("logs"));
            Log.e("tabe",String.valueOf(prods.length()));

                JSONObject row = prods.getJSONObject(0);
            JSONObject row2 = prods.getJSONObject(1);


                t=row.getBoolean("inv_with");
                if(t)
                    t1.setText("Invested");
                else
                    t1.setText("Withdrawn");
                btc = row.getInt("btc");
               inr = row.getInt("inr");
               date=row.getString("date");

                t2.setText(""+btc);
                t3.setText(""+inr);
                t4.setText(""+date.substring(0,10));
            t=row2.getBoolean("inv_with");
            if(t)
                t5.setText("Invested");
            else
                t5.setText("Withdrawn");
            btc = row2.getInt("btc");
            inr = row2.getInt("inr");
            date=row2.getString("date");

            t6.setText(""+btc);
            t7.setText(""+inr);
            t8.setText(""+date.substring(0,10));
            if(prods.length()==3) {
                JSONObject row3 = prods.getJSONObject(2);

                t = row3.getBoolean("inv_with");

                if (t)
                    t9.setText("Invested");
                else
                    t9.setText("Withdrawn");
                btc = row3.getInt("btc");
                inr = row3.getInt("inr");
                date = row3.getString("date");

                t10.setText("" + btc);
                t11.setText("" + inr);
                t12.setText("" + date.substring(0, 10));
            }
            else
                if(prods.length()==4) {
                    JSONObject row4 = prods.getJSONObject(3);

                    t = row4.getBoolean("inv_with");
                    if (t)
                        t13.setText("Invested");
                    else
                        t13.setText("Withdrawn");
                    btc = row4.getInt("btc");
                    inr = row4.getInt("inr");
                    date = row4.getString("date");

                    t14.setText("" + btc);
                    t15.setText("" + inr);
                    t16.setText("" + date.substring(0, 10));

                }
                else
                if(prods.length()==5) {
                    JSONObject row5 = prods.getJSONObject(4);

                    t = row5.getBoolean("inv_with");
                    if (t)
                        t17.setText("Invested");
                    else
                        t17.setText("Withdrawn");
                    btc = row5.getInt("btc");
                    inr = row5.getInt("inr");
                    date = row5.getString("date");

                    t18.setText("" + btc);
                    t19.setText("" + inr);
                    t20.setText("" + date.substring(0, 10));

                }
        }
        catch(Exception e){}
    }

}
