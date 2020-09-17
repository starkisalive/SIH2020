package com.test.datafetchmultiple;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.google.android.material.navigation.NavigationView;
import com.test.datafetchmultiple.ui.home.HomeFragment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private AppBarConfiguration mAppBarConfiguration;
    MapFragment mapFragment;
    private String myString = "hello";
    private String myEmail = "hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.icongaddhe);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        TextView text = (TextView) headerView.findViewById(R.id.afterLoginUser);

        TextView emaiText = (TextView) headerView.findViewById(R.id.emailUser);
        final String user_name = getIntent().getStringExtra("user_name");
        final String email_add = getIntent().getStringExtra("email_add");
        myString = user_name;
        myEmail = email_add;
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        text.setText(user_name);
        emaiText.setText("contact@teamvektor.com");

        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);


    }
    public String getMyData() {
        return myString;
    }
    public String getMyEmail() {
        return myEmail;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.nav, menu);
        return true;
    }
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.track){
            Intent intent = new Intent(NavActivity.this, TrackActivity.class);
            intent.putExtra("user_name",myString);
            intent.putExtra("email_add",myString);
            startActivity(intent);
        }
        else if(id == R.id.pothole){
            Intent intent = new Intent(NavActivity.this, potholeNear.class);
            startActivity(intent);
        }
        else if(id == R.id.share){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }
        else if(id == R.id.about) {
            Intent intent = new Intent(NavActivity.this, AboutActivity.class);
            startActivity(intent);
        }
        else if(id == R.id.reward){
                Intent intent = new Intent(NavActivity.this, rewards.class);
                intent.putExtra("user_name",myString);
                startActivity(intent);

        }else if(id == R.id.logout){

            SharedPreferences preferences = getSharedPreferences("checkbox",MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("remember","false");
            editor.apply();
            Intent intent = new Intent(NavActivity.this,MainActivity.class);
            startActivity(intent);
            finish();

        }
        else if(id == R.id.navhome){
            HomeFragment homeFragment = new HomeFragment();
            Bundle bundle = new Bundle();
            bundle.putString("edttext", "From Activity");
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, homeFragment).commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}
