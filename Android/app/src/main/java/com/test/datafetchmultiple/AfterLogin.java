package com.test.datafetchmultiple;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

public class AfterLogin extends AppCompatActivity {

    DrawerLayout dl;
    NavigationView nv;
    Toolbar toolbar;
    Button in;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_after_login);

        dl = findViewById(R.id.drawer_layout);
        nv = findViewById(R.id.nav_view);
        View headerView = nv.getHeaderView(0);
        TextView text = (TextView) headerView.findViewById(R.id.afterLoginUser);
        //text = findViewById(R.id.Pusername);
        String userName = getIntent().getStringExtra("user_name");
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,dl,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        dl.addDrawerListener(toggle);
        toggle.syncState();

        text.setText(userName);

    }
}
