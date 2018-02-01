package com.duccipopi.guildherald.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.view.fragments.ListFragmentsPagerAdapter;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new ListFragmentsPagerAdapter(getSupportFragmentManager()));

    }

}
