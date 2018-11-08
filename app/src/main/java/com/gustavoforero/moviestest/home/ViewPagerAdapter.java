package com.gustavoforero.moviestest.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private static final String[] titles = new String[]{"Popular", "Top Rated", "Upcoming"};
    private static final int NUM_ITEMS = 3;


    ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


    @Override
    public Fragment getItem(int position) {
        return MovieItemFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }
}

