package com.duccipopi.guildherald.view.base;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import java.util.Map;

/**
 * Created by ducci on 03/02/2018.
 */

public class PageNavigationLinker extends ViewPager.SimpleOnPageChangeListener
        implements BottomNavigationView.OnNavigationItemSelectedListener {

    private ViewPager mViewPager;
    private BottomNavigationView mNavigationView;
    private Map<Integer, Integer> mLinks;

    public PageNavigationLinker(@NonNull ViewPager viewPager,
                                @NonNull BottomNavigationView navigationView,
                                @NonNull Map<Integer, Integer> links) {
        mViewPager = viewPager;
        mNavigationView = navigationView;
        mLinks = links;

        mViewPager.addOnPageChangeListener(this);
        mNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        for (Map.Entry<Integer, Integer> entry : mLinks.entrySet()) {
            if (entry.getValue() == item.getItemId()) {
                mViewPager.setCurrentItem(entry.getKey());
                return true;
            }
        }
        return false;
    }

    @Override
    public void onPageSelected(int position) {
        mNavigationView.setSelectedItemId(mLinks.get(position));
    }

}