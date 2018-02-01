package com.duccipopi.guildherald.view.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ducci on 01/02/2018.
 */

public class ListFragmentsPagerAdapter extends FragmentPagerAdapter {

    private static final Fragment[] FRAGMENTS = {
            new CharacterListFragment(),
            new GuildListFragment()
    };

    public ListFragmentsPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public Fragment getItem(int i) {
        return FRAGMENTS[i];
    }

    @Override
    public int getCount() {
        return FRAGMENTS.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return FRAGMENTS[position].getClass().getSimpleName();
    }
}
