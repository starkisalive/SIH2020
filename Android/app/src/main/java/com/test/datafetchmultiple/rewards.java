package com.test.datafetchmultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class rewards extends AppCompatActivity {

    TextView reward,peb;
    int point;
    String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        final LottieAnimationView lottiLoad = findViewById(R.id.lottieAnimationView);
        reward = findViewById(R.id.reward);
        peb = findViewById(R.id.pebb);
        name = getIntent().getStringExtra("user_name");
        getreward();
        lottiLoad.setProgress(50F);
        lottiLoad.playAnimation();
    }

    private void getreward() {

        String url = "http://vektor.soumit.tech/rewards.php?name="+name;

//        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("status");
                            point = jsonObject.getInt("sts");
                            if(success.equals("Sucess")){
                                reward.setText(Integer.toString(point));
                                peb.setVisibility(View.VISIBLE);

                                Toast.makeText(rewards.this,"Status Updated",Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(rewards.this,"Some error Occured",Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            Toast.makeText(rewards.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                //ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(rewards.this, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);



    }
}
