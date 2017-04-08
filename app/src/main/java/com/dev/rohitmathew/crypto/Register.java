package com.dev.rohitmathew.crypto;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import static android.R.attr.onClick;

public class Register extends AppCompatActivity {
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        b = (Button) findViewById(R.id.button);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });
    }

    private void register() {
        EditText editText = (EditText) findViewById(R.id.editText3);
        EditText editText2 = (EditText) findViewById(R.id.editText4);
        EditText editText3 = (EditText) findViewById(R.id.editText5);
        final String email = editText.getText().toString();
        final String password = editText2.getText().toString();
        String cpass = editText3.getText().toString();
        Log.e("email", email);
        Log.e("password", password);


        if (password.equals(cpass)) {
            String url = "http://prox-hariaakash.rhcloud.com/api/users/register";

            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject person = new JSONObject(response);
                                boolean ct = person.getBoolean("status");
                                Log.e("ct value", Boolean.toString(ct));
                                if (ct) {
                                    Toast.makeText(getApplicationContext(), "Successfully Registered!", Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(Register.this, Login.class);
                                    startActivity(i);
                                    finish();
                                } else {
                                    String msg = person.getString("msg");
                                    AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
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
                    params.put("email", email);
                    params.put("password", password);
                    Log.i("params of my service", params.toString());
                    return params;
                }
            };
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(10000,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

            RequestQueue requestQueue = Volley.newRequestQueue(Register.this);
            requestQueue.add(stringRequest);

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(Register.this);
            alert.setTitle("Incorrect Passwords");
            alert.setMessage("Passwords don't match");
            alert.setPositiveButton("OK", null);
            alert.show();
        }
    }
}



