package com.gustavoforero.moviestest.di;

import com.gustavoforero.moviestest.home.MovieItemFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;


@Module
public abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract MovieItemFragment contributeMovieListFragment();
}
