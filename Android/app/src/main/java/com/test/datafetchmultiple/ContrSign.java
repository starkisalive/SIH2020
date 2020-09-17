package com.test.datafetchmultiple;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContrSign extends AppCompatActivity {

    EditText name;
    EditText pass;
    Button signin,out;
    String myString;
    CheckBox chkbox;
    private ProgressBar loading;
    String url = "http://vektor.soumit.tech/logincont.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contr_sign);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        signin = findViewById(R.id.signin);
        chkbox = findViewById(R.id.checkBox);
//        loading = findViewById(R.id.progresslogin);


        SharedPreferences preferences = getSharedPreferences("checkbox1", MODE_PRIVATE);
        String chexkbox = preferences.getString("remember1","");
        String user = preferences.getString("userc","");
        Toast.makeText(ContrSign.this,user,Toast.LENGTH_SHORT).show();

        if(chexkbox.equals("true")){
            Intent intent = new Intent(ContrSign.this,trackcont.class);
            intent.putExtra("user_name", user);

            startActivity(intent);
        }else if(chexkbox.equals("false")){
            Toast.makeText(ContrSign.this,"Please Sign In",Toast.LENGTH_SHORT).show();
        }

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUser = name.getText().toString().trim();
                String mPass = pass.getText().toString().trim();

                myString = mUser;

                if (!mUser.isEmpty() || !mPass.isEmpty()) {
                    login(mUser, mPass);
                } else {
                    name.setError("Please Enter Email");
                    pass.setError("Please Enter Password");
                }
            }
        });
        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox1",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember1","true");
                    editor.putString("userc",name.getText().toString());
                    editor.apply();
                    Toast.makeText(ContrSign.this,"You will be logged in",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember1","false");
                    editor.apply();

                }

            }
        });
    }

    public String getMyData() {
        return myString;
    }

    public void login(final String mUser, final String mPass) {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(ContrSign.this, "Successful."+response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for( int i=0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String user = object.getString("user").trim();

                                    Toast.makeText(ContrSign.this, "Login Successful. \nUser :"+user, Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(ContrSign.this,trackcont.class);
                                    intent.putExtra("user_name", myString);
                                    startActivity(intent);
                                }

                            }
                            else{
//                                loading.setVisibility(View.GONE);
                                Toast.makeText(ContrSign.this, "User Id Or Password Incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
//                            loading.setVisibility(View.GONE);
                            Toast.makeText(ContrSign.this, "Login Unsuccessful 111s."+e.toString(), Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loading.setVisibility(View.GONE);
                        Toast.makeText(ContrSign.this, "Login Unsuccessful 1."+error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user",mUser);
                params.put("pass",mPass);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}


