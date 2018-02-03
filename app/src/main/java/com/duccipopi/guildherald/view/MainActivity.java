package com.duccipopi.guildherald.view;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.view.fragments.ListFragmentsPagerAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(new ListFragmentsPagerAdapter(getSupportFragmentManager()));

        // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
        mAdView = findViewById(R.id.adView);

        // Create an ad request.
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();

        // Start loading the ad.
        mAdView.loadAd(adRequestBuilder.build());

    }

    @Override
    protected void onDestroy() {
        mAdView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdView.resume();
    }

}
