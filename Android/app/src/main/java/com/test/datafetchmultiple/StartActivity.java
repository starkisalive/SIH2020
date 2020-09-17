package com.test.datafetchmultiple;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.airbnb.lottie.LottieAnimationView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    Button user,cont;
    Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        final LottieAnimationView lottiLoad = findViewById(R.id.load);
        user = findViewById(R.id.user);
        cont = findViewById(R.id.cont);


        user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lottiLoad.setProgress(50F);
                lottiLoad.playAnimation();
                user();


            }
        });
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                lottiLoad.playAnimation();
                contractor();
            }
        });
    }

    public void user(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                finish();
            }
        },2000);
    }

    public void contractor(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(StartActivity.this,ContrSign.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in,R.anim.slide_out);
                finish();
            }
        },2000);
    }
}
