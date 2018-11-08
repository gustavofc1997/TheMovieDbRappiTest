package com.gustavoforero.moviestest.home;

import android.view.View;

import com.gustavoforero.moviestest.data.model.entity.Movie;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public interface MovieItemCallback {
    void onClickMovieItem(Movie movie, View sharedView);
}
