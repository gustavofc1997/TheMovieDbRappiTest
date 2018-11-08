package com.gustavoforero.moviestest.data.remote;


import com.gustavoforero.moviestest.data.remote.model.MoviesResponse;
import com.gustavoforero.moviestest.data.remote.model.TrailersResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public interface TheMovieDBApi {

    String URL_BASE = "https://api.themoviedb.org/";
    String API_KEY = "6d1b16b829110acfda3c18079e022ff3";

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @GET("3/discover/movie?sort_by=popularity.desc&api_key=" + API_KEY)
    Call<MoviesResponse> getPopularMovies(@Query("page") int page);

    @GET("3/movie/{Id}/videos?api_key="+API_KEY)
    Call<TrailersResponse>getMovieVideoId(@Path("Id") String movieId);

    @Headers({"Content-Type: application/json;charset=utf-8"})
    @GET("3/discover/movie?sort_by=vote_count.desc&api_key=" + API_KEY)
    Call<MoviesResponse> getTopMovies(@Query("page") int page);


    @Headers({"Content-Type: application/json;charset=utf-8"})
    @GET("3/movie/upcoming?api_key=" + API_KEY)
    Call<MoviesResponse> getUpcomingMovies(@Query("page") int page);

}
