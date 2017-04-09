package com.dev.rohitmathew.crypto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    Button b;
    static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_login);

        sharedPreferences = this.getSharedPreferences("text", Context.MODE_PRIVATE);


        b = (Button) findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
            });
        }
                             public void login(){
                                 EditText editText = (EditText) findViewById(R.id.editText);
                                 EditText editText2 = (EditText) findViewById(R.id.editText2);
                                 final String email = editText.getText().toString();
                                 final String password = editText2.getText().toString();
                                 Log.e("email", email);
                                 Log.e("password", password);

                String url = "http://prox-hariaakash.rhcloud.com/api/users/login";

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONObject person = new JSONObject(response);
                                    boolean ct = person.getBoolean("status");
                                    String authKey = person.getString("authKey");
                                    sharedPreferences.edit().putString("authKey",authKey).apply();
                                    Log.e("ct value", Boolean.toString(ct));
                                    if (ct) {
                                        Toast.makeText(getApplicationContext(), "Signing In", Toast.LENGTH_LONG).show();
                                        Intent i = new Intent(Login.this, MainActivity.class);
                                        startActivity(i);
                                    } else {
                                        String msg = person.getString("msg");
                                        AlertDialog.Builder alert = new AlertDialog.Builder(Login.this);
                                        alert.setTitle("Incorrect Credentials");
                                        alert.setMessage(msg);
                                        alert.setPositiveButton("OK", null);
                                        alert.show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Log.e("Error", e.getMessage());
                                }
                                System.out.println(response);
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //mTextView.setText("That didn't work!");
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        Log.e("email", email);
                        Log.e("password", password);
                        params.put("email", email);
                        params.put("password", password);


                        Log.i("params of my service", params.toString());
                        return params;
                    }
                };
                stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                        DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                        DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);


    }

    public void change(View view)
    {
        Intent i=new Intent(Login.this,Register.class);
         startActivity(i);

    }
}
