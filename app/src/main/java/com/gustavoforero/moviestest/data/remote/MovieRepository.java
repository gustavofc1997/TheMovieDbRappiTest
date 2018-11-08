package com.gustavoforero.moviestest.data.remote;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.gustavoforero.moviestest.data.NetworkBoundResource;
import com.gustavoforero.moviestest.data.Resource;
import com.gustavoforero.moviestest.data.model.dao.MovieDao;
import com.gustavoforero.moviestest.data.model.dao.TrailerDao;
import com.gustavoforero.moviestest.data.model.entity.Movie;
import com.gustavoforero.moviestest.data.remote.model.MoviesResponse;
import com.gustavoforero.moviestest.data.model.entity.Trailer;
import com.gustavoforero.moviestest.data.remote.model.TrailersResponse;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;


public class MovieRepository {

    private final MovieDao mMovieDap;
    private final TrailerDao mTrailerDao;
    private final TheMovieDBApi mApiService;
    public static String UPCOMING = "Upcoming";
    public static String TOP_RATED = "Top";
    public static String POPULAR = "Popular";

    @Inject
    public MovieRepository(MovieDao mMovieDap, TrailerDao mTrailerDao, TheMovieDBApi mApiService) {
        this.mMovieDap = mMovieDap;
        this.mTrailerDao = mTrailerDao;
        this.mApiService = mApiService;
    }


    public LiveData<Resource<List<Movie>>> loadPopularMovies() {
        return new NetworkBoundResource<List<Movie>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                for (Movie result : item.getResults()) {
                    result.setMovieCategory(POPULAR);
                }
                mMovieDap.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return mMovieDap.loadMoviesByCat(POPULAR);
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return mApiService.getPopularMovies(1);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Movie>>> loadTopRatedMovies() {
        return new NetworkBoundResource<List<Movie>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                for (Movie result : item.getResults()) {
                    result.setMovieCategory(TOP_RATED);
                }
                mMovieDap.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return mMovieDap.loadMoviesByCat(TOP_RATED);
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return mApiService.getTopMovies(1);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Movie>>> loadMoviesUpcoming() {
        return new NetworkBoundResource<List<Movie>, MoviesResponse>() {

            @Override
            protected void saveCallResult(@NonNull MoviesResponse item) {
                for (Movie result : item.getResults()) {
                    result.setMovieCategory(UPCOMING);
                }
                mMovieDap.saveMovies(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Movie>> loadFromDb() {
                return mMovieDap.loadMoviesByCat(UPCOMING);
            }

            @NonNull
            @Override
            protected Call<MoviesResponse> createCall() {
                return mApiService.getUpcomingMovies(1);
            }
        }.getAsLiveData();
    }

    public LiveData<Resource<List<Trailer>>> getMovieTrailers(String id) {
        return new NetworkBoundResource<List<Trailer>, TrailersResponse>() {

            @Override
            protected void saveCallResult(@NonNull TrailersResponse item) {
                for (Trailer result : item.getResults()) {
                    result.setMovieId(id);
                }
                mTrailerDao.saveTrailers(item.getResults());
            }

            @NonNull
            @Override
            protected LiveData<List<Trailer>> loadFromDb() {
                return mTrailerDao.loadTrailersByMovie(id);
            }

            @NonNull
            @Override
            protected Call<TrailersResponse> createCall() {
                return mApiService.getMovieVideoId(id);
            }
        }.getAsLiveData();
    }

    public LiveData<Movie> getMovie(int id) {
        return mMovieDap.getMovie(id);
    }
}
