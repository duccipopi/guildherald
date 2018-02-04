package com.duccipopi.guildherald.view.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ducci on 01/02/2018.
 */

public class ListFragmentsPagerAdapter extends FragmentPagerAdapter {

    private final Fragment[] mFragments;

    public ListFragmentsPagerAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        mFragments = fragments;
    }


    @Override
    public Fragment getItem(int i) {
        return mFragments[i];
    }

    @Override
    public int getCount() {
        return mFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments[position].getClass().getSimpleName();
    }
}
