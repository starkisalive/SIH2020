package com.test.datafetchmultiple;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.test.datafetchmultiple.mFragments.CrimeFragment;
import com.test.datafetchmultiple.mFragments.DocumentaryFragment;
import com.test.datafetchmultiple.mFragments.DramaFrgament;
import com.test.datafetchmultiple.mFragments.MyPagerAdapter;


public class trackcont extends AppCompatActivity implements TabLayout.OnTabSelectedListener, ViewPager.OnPageChangeListener {


    ViewPager vp;
    TabLayout tabLayout;
    private String myString = "hello";
    FloatingActionButton fab;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackcont);
        Toolbar toolbar = findViewById(R.id.toolbar);
        logout = findViewById(R.id.logout);
        setSupportActionBar(toolbar);
        final String user_name = getIntent().getStringExtra("user_name");
        myString = user_name;

        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(trackcont.this,contMap.class);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = getSharedPreferences("checkbox1",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("remember1","false");
                editor.apply();
                Intent intent = new Intent(trackcont.this,ContrSign.class);
                startActivity(intent);
                finish();
            }
        });

        //VIEWPAGER
        vp= (ViewPager) findViewById(R.id.mViewpager_ID);
        this.addPages();

        //TABLAYOUT
        tabLayout= (TabLayout) findViewById(R.id.mTab_ID);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setupWithViewPager(vp);
        tabLayout.setOnTabSelectedListener(this);

    }

    public String getMyData() {
        return myString;
    }

    private void addPages()
    {
        MyPagerAdapter pagerAdapter=new MyPagerAdapter(this.getSupportFragmentManager());
        pagerAdapter.addFragment(new CrimeFragment());
        pagerAdapter.addFragment(new DramaFrgament());
        pagerAdapter.addFragment(new DocumentaryFragment());

        //SET ADAPTER TO VP
        vp.setAdapter(pagerAdapter);
    }

    public void onTabSelected(TabLayout.Tab tab) {
        vp.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
