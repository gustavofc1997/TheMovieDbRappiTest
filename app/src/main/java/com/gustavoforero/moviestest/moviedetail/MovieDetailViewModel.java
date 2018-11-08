package com.gustavoforero.moviestest.moviedetail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.gustavoforero.moviestest.data.Resource;
import com.gustavoforero.moviestest.data.model.entity.Movie;
import com.gustavoforero.moviestest.data.model.entity.Trailer;
import com.gustavoforero.moviestest.data.remote.MovieRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public class MovieDetailViewModel extends ViewModel {
    private final MovieRepository movieRepository;

    @Inject
    MovieDetailViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    LiveData<Movie> getMovieById(int id) {
        return movieRepository.getMovie(id);
    }

    LiveData<Resource<List<Trailer>>> getTrailers(String id) {
        return movieRepository.getMovieTrailers(id);
    }


}
