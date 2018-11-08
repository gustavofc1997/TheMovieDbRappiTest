package com.gustavoforero.moviestest.di;

import com.gustavoforero.moviestest.home.MainActivity;
import com.gustavoforero.moviestest.moviedetail.MovieDetailActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {

    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector
    abstract MovieDetailActivity movieDetailActivity();
}
