package com.duccipopi.guildherald.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.duccipopi.guildherald.R;
import com.duccipopi.guildherald.model.firebase.Authenticator;
import com.duccipopi.guildherald.util.AdMobManager;
import com.duccipopi.guildherald.view.base.ListFragmentsPagerAdapter;
import com.duccipopi.guildherald.view.base.PageNavigationLinker;
import com.duccipopi.guildherald.view.fragments.CharacterListFragment;
import com.duccipopi.guildherald.view.fragments.GuildListFragment;
import com.google.android.gms.ads.AdView;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {

    private static final int CHARACTER_FRAGMENT_INDEX = 0;
    private static final int GUILD_FRAGMENT_INDEX = 1;

    private AdView mAdView;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Authenticator.REQUEST_SIGNING) {
            if (resultCode != RESULT_OK) finish();

            setContentView(R.layout.activity_main);

            // View pager setup
            final ViewPager viewPager = findViewById(R.id.pager);
            viewPager.setAdapter(new ListFragmentsPagerAdapter(getSupportFragmentManager(),
                    new Fragment[]{
                            new CharacterListFragment(),
                            new GuildListFragment()
                    }));

            // Bottom navigation setup
            BottomNavigationView navigationView = findViewById(R.id.navigation);

            // Link View pager and Bottom navigation
            Map<Integer, Integer> links = new HashMap<>();

            links.put(CHARACTER_FRAGMENT_INDEX, R.id.navigation_character);
            links.put(GUILD_FRAGMENT_INDEX, R.id.navigation_guild);

            PageNavigationLinker linker = new PageNavigationLinker(viewPager, navigationView, links);

            // Floating button setup
            FloatingActionButton fab = findViewById(R.id.buttonAdd);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), AddActivity.class);

                    intent.setAction(viewPager.getCurrentItem() == CHARACTER_FRAGMENT_INDEX
                            ? ActivityContract.ACTION_ADD_CHARACTER
                            : ActivityContract.ACTION_ADD_GUILD);

                    startActivity(intent);

                }
            });

            // Ad View setup
            // Create a banner ad. The ad size and ad unit ID must be set before calling loadAd.
            mAdView = findViewById(R.id.adView);

            // Start loading the ad.
            mAdView.loadAd(AdMobManager.getAdRequest());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!Authenticator.isUserAuthenticated()) {
            Authenticator.startAuthenticationUI(this);
        }

    }

    @Override
    protected void onDestroy() {
        if (mAdView != null) mAdView.destroy();
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        if (mAdView != null) mAdView.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAdView != null) mAdView.resume();
    }

}
