package com.test.datafetchmultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class signup extends AppCompatActivity {

    private EditText name,email,password,c_password;
    private Button btn,in;
    private ProgressBar loading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

//        loading = findViewById(R.id.progresslogin);
        name = findViewById(R.id.username);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);
//        c_password = findViewById(R.id.psw);
        btn = findViewById(R.id.out);
        in = findViewById(R.id.in);

        in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(signup.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });
    }

    private void Regist() {
//        loading.setVisibility(View.VISIBLE);
//        btn.setVisibility(View.GONE);

        final String namee = this.name.getText().toString().trim();
        final String emaill = this.email.getText().toString().trim();
        final String passwordd = this.password.getText().toString().trim();

        String URL_Regist="http://vektor.soumit.tech/register.php?user="+namee+"&email="+emaill+"&pass="+passwordd;

        Toast.makeText(signup.this, namee, Toast.LENGTH_SHORT).show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_Regist, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("sts");
                    Toast.makeText(signup.this, success, Toast.LENGTH_LONG).show();
                    if (success.equals("1")){
                        Toast.makeText(signup.this, "Register Success", Toast.LENGTH_SHORT).show();
//                        loading.setVisibility(View.GONE);
//                        name.setText("");
//                        email.setText("");
//                        password.setText("");
//                        c_password.setText("");
                        Intent intent = new Intent(signup.this, MainActivity.class);
                        startActivity(intent);
                    }
                    else if(success.equals("-1")){
                        Toast.makeText(signup.this, "User Already Exist", Toast.LENGTH_SHORT).show();
//                        loading.setVisibility(View.GONE);
                        name.setText("");
//                        btn.setVisibility(View.VISIBLE);
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                    Toast.makeText(signup.this, "Register Error"+ e.toString(), Toast.LENGTH_SHORT).show();
//                    loading.setVisibility(View.GONE);
//                    btn.setVisibility(View.VISIBLE);
                }
            }
        },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        Toast.makeText(signup.this, "Register Erro"+ error.toString(), Toast.LENGTH_SHORT).show();
//                        loading.setVisibility(View.GONE);
//                        btn.setVisibility(View.VISIBLE);
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
