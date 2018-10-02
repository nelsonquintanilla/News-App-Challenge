package com.applaudostudios.newsapp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.applaudostudios.newsapp.adapters.FragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.viewpager);
        FragmentPagerAdapter adapter = new FragmentPagerAdapter(getSupportFragmentManager());

        //Sets the adapter to the ViewPager
        viewPager.setAdapter(adapter);
        viewPager.getCurrentItem();

        // Attaches the TabLayout to the ViewPager
        tabLayout.setupWithViewPager(viewPager);

        // Removes shadow from the action bar
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
        }
    }


}
