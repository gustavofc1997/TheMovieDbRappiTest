package com.gustavoforero.moviestest.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.gustavoforero.moviestest.data.Resource;
import com.gustavoforero.moviestest.data.model.entity.Movie;
import com.gustavoforero.moviestest.data.remote.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public class MovieListViewModel extends ViewModel {
    private final LiveData<Resource<List<Movie>>> mPopularMovies;
    private final LiveData<Resource<List<Movie>>> mUpcomingMovies;
    private final LiveData<Resource<List<Movie>>> mTopRatedMovies;

    @Inject
    MovieListViewModel(MovieRepository movieRepository) {
        mUpcomingMovies = movieRepository.loadMoviesUpcoming();
        mTopRatedMovies = movieRepository.loadTopRatedMovies();
        mPopularMovies = movieRepository.loadPopularMovies();
    }

    public LiveData<Resource<List<Movie>>> getmPopularMovies() {
        return mPopularMovies;
    }

    public LiveData<Resource<List<Movie>>> getmUpcomingMovies() {
        return mUpcomingMovies;
    }

    public LiveData<Resource<List<Movie>>> getmTopRatedMovies() {
        return mTopRatedMovies;
    }


}
