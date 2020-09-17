package com.test.datafetchmultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class WorkSts extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String sts;
    String idwork;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_sts);

        spinner = findViewById(R.id.spinner);

        idwork = getIntent().getStringExtra("lat");
        spinner.setOnItemSelectedListener(this);


    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        sts = parent.getSelectedItem().toString();
        if(sts.equals("Completed")|| sts.equals("Work Started")){
            Toast.makeText(this, idwork + sts, Toast.LENGTH_SHORT).show();
            String url = "http://vektor.soumit.tech/updatecont.php?id="+idwork+"&sts="+sts;

//        Toast.makeText(getApplicationContext(),"",Toast.LENGTH_LONG).show();

            StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("status");
                                if(success.equals("Sucess")){
                                    Toast.makeText(WorkSts.this,"Status Updated",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(WorkSts.this,"Some error Occured",Toast.LENGTH_SHORT).show();
                                }


                            } catch (JSONException e) {
                                Toast.makeText(WorkSts.this, "GOOD RESPONSE BUT JAVA CAN'T PARSE JSON IT RECEIEVED. " + e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    },
                    //ERROR
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Toast.makeText(WorkSts.this, "UNSUCCESSFUL :  ERROR IS : "+error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
