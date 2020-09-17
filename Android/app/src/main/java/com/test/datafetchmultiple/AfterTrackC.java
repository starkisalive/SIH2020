package com.test.datafetchmultiple;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class AfterTrackC extends AppCompatActivity {

    String image, land, id ;
    Context context;
    String budge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_track_c);

//        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
//                new IntentFilter("message"));

        TextView text = (TextView) findViewById(R.id.text);
        Button accept = (Button) findViewById(R.id.accept);
        Button reject = (Button) findViewById(R.id.reject);
        final ImageView img = (ImageView) findViewById(R.id.imageView);

        land = getIntent().getStringExtra("name");
        image = getIntent().getStringExtra("url");
        id = getIntent().getStringExtra("lat");

        context = this;
        Toast.makeText(getApplicationContext(),image + land + id,Toast.LENGTH_LONG).show();

        text.setText(land);
        Picasso.get().load(image).placeholder(R.drawable.placeholder).into(img);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Dialog builder = new Dialog(context);
                builder.setContentView(R.layout.custom_layout);
                builder.setTitle("Enter Your Budget");
                final EditText budget = builder.findViewById(R.id.budg);
                Button apply = builder.findViewById(R.id.btn);



                apply.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        budge = budget.getText().toString().trim();
//                        Toat.makeText(getApplicationContext(),budge,Toast.LENGTH_LONG).show();
                        push(budge, id);
                    }
                });
                builder.show();


            }
        });

        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    delete(id);
                    finish();

            }
        });

    }

    private void delete(String id) {

        String url = "http://teamvektor.tk:8080/delete.php?url="+id+"";

//        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("status");
                            if(success.equals("Sucess")){
                                Toast.makeText(AfterTrackC.this,"Okaay Thank You",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(AfterTrackC.this,"Some error Occured",Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            Toast.makeText(AfterTrackC.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                //ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AfterTrackC.this, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void push(String budget, String id) {

        String url = "http://vektor.soumit.tech/intrest.php?url="+id+"&budget="+budget+"&choice=intrested";

        Toast.makeText(getApplicationContext(),budget + id,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("status");
                            if(success.equals("Sucess")){
                                Toast.makeText(AfterTrackC.this,"Sucessfully Submited",Toast.LENGTH_SHORT).show();
                                finish();
                            }
                            else{
                                Toast.makeText(AfterTrackC.this,"Some error Occured",Toast.LENGTH_SHORT).show();
                            }


                        } catch (JSONException e) {
                            Toast.makeText(AfterTrackC.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. " + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                },
                //ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AfterTrackC.this, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

//    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            // Get extra data included in the Intent
//            image = intent.getStringExtra("image");
//            land = intent.getStringExtra("land");
//            Toast.makeText(getApplicationContext(),image + land,Toast.LENGTH_LONG).show();
//        }
//    };
}

