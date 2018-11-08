package com.gustavoforero.moviestest.home;

import android.os.Bundle;

import com.gustavoforero.moviestest.BaseActivity;
import com.gustavoforero.moviestest.R;
import com.gustavoforero.moviestest.databinding.ActivityMainBinding;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public class MainActivity extends BaseActivity<ActivityMainBinding> {

    @Override
    public int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding.viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        dataBinding.tabs.setupWithViewPager(dataBinding.viewPager);
        dataBinding.viewPager.setOffscreenPageLimit(3);
    }
}
