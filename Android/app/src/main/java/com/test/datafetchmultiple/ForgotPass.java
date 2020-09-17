package com.test.datafetchmultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.FontRequest;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.test.datafetchmultiple.mData.TVShow;
import com.test.datafetchmultiple.mFragments.CrimeFragment;
import com.test.datafetchmultiple.ui.home.HomeFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ForgotPass extends AppCompatActivity {

    EditText user,pass,cpass;
    Button check,change;
    String name;
    int result;
    String stss;
    ProgressBar progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        user = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        cpass = findViewById(R.id.cpass);
        check = findViewById(R.id.check);
        change = findViewById(R.id.change);
        progress = findViewById(R.id.progressBar);


        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = user.getText().toString();

                checkuser(u);
                }


        });
    }

    private void checkuser(final String u) {


        progress.setVisibility(View.VISIBLE);
        String url = "http://vektor.soumit.tech/forgot.php?us="+u;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("result");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject obj = jsonArray.getJSONObject(i);
                                String name1 = obj.getString("user");
                                name = name1;
                            }
                            progress.setVisibility(View.GONE);
                            Toast.makeText(ForgotPass.this,"User Found "+name,Toast.LENGTH_SHORT).show();
                            result = 1;
                            if(name!=null) {
                                pass.setVisibility(View.VISIBLE);
                                cpass.setVisibility(View.VISIBLE);
                                check.setVisibility(View.INVISIBLE);
                                change.setVisibility(View.VISIBLE);
                                change.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {

                                        String p = pass.getText().toString();
                                        String cp = cpass.getText().toString();

//                                        changepass();
                                        //
                                        if(p.equals(cp)) {
//                                            Toast.makeText(ForgotPass.this,"Hey Pass Entered "+name,Toast.LENGTH_SHORT).show();
                                            changepass(name,p);
                                        }else{
                                            progress.setVisibility(View.GONE);
                                            Toast.makeText(ForgotPass.this,"Passwords Didn't Match. Please try Again"+name,Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                        } catch (JSONException e) {
                            progress.setVisibility(View.GONE);
                            Toast.makeText(ForgotPass.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                //ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(ForgotPass.this, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void changepass(final String name, String p) {

        progress.setVisibility(View.VISIBLE);
        String url = "http://192.168.0.105/audiohtml/changfor.php?us="+name+"&pas="+p;

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("status");
                            if(success.equals("Sucess")){
                                progress.setVisibility(View.GONE);
                                Toast.makeText(ForgotPass.this,"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(ForgotPass.this,MainActivity.class);
                                startActivity(intent);
                            }
                            else{
                                progress.setVisibility(View.GONE);
                                Toast.makeText(ForgotPass.this,"Password Change Unsucessfull",Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            progress.setVisibility(View.GONE);
                             Toast.makeText(ForgotPass.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                //ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.setVisibility(View.GONE);
                        Toast.makeText(ForgotPass.this, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }


}
