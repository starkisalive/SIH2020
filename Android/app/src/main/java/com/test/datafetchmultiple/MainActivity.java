package com.test.datafetchmultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText pass;
    CheckBox chkbox;
    Button signin,out,loginwith;
    static String mUser;
    static String mPass;
    String mEmail;
    private ProgressBar loading;
    private SharedPreferences sharedpreferences;
    String url = "http://vektor.soumit.tech/login.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);
        signin = findViewById(R.id.signin);
        out = findViewById(R.id.out);
        loginwith = findViewById(R.id.forgotp);
        chkbox = findViewById(R.id.checkBox);
//        loading = findViewById(R.id.progresslogin);




        SharedPreferences preferences = getSharedPreferences("checkbox", MODE_PRIVATE);
        String chexkbox = preferences.getString("remember","");
        String user = preferences.getString("user","");
        String email = preferences.getString("email","");
        Toast.makeText(MainActivity.this,user,Toast.LENGTH_SHORT).show();

        if(chexkbox.equals("true")){
            Intent intent = new Intent(MainActivity.this,NavActivity.class);
            intent.putExtra("user_name", user);
            intent.putExtra("email_add", email);
            startActivity(intent);
        }else if(chexkbox.equals("false")){
            Toast.makeText(MainActivity.this,"Please Sign In",Toast.LENGTH_SHORT).show();
        }

        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, signup.class);
                startActivity(intent);
            }
        });

        loginwith.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPass.class);
                startActivity(intent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mUser = name.getText().toString().trim();
                mPass = pass.getText().toString().trim();

                if (!mUser.isEmpty() || !mPass.isEmpty()) {
                    login(mUser, mPass);
                } else {
                    name.setError("Please Enter Username");
                    pass.setError("Please Enter Password");
                }
            }
        });

        chkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if(compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","true");
                    editor.putString("user",name.getText().toString());
                    editor.putString("email",mEmail);
                    editor.apply();
                    Toast.makeText(MainActivity.this,"You will be logged in",Toast.LENGTH_SHORT).show();

                }else if(!compoundButton.isChecked()){

                    SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember","false");
                    editor.apply();

                }

            }
        });
    }

    public void login(final String mUser, final String mPass) {
//        loading.setVisibility(View.VISIBLE);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity.this, "Successful."+response, Toast.LENGTH_SHORT).show();

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if(success.equals("1")){
                                for( int i=0; i < jsonArray.length(); i++){

                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String user = object.getString("user").trim();

                                    String email = object.getString("email").trim();
                                    mEmail = email;
//                                    loading.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Login Successful. \nUser :"+user, Toast.LENGTH_SHORT).show();
                                   Intent intent = new Intent(MainActivity.this,NavActivity.class);
                                    intent.putExtra("user_name", user);
                                    intent.putExtra("email_add", email);
                                    startActivity(intent);
                                }

                            }
                            else{
//                                loading.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "User Id Or Password Incorrect.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e){
                            e.printStackTrace();
//                            loading.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Login Unsuccessful 111s."+e.toString(), Toast.LENGTH_LONG).show();

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        loading.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Login Unsuccessful 1."+error.toString(), Toast.LENGTH_SHORT).show();

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

