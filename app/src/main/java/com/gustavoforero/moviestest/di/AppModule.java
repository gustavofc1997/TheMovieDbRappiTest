package com.gustavoforero.moviestest.di;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.gustavoforero.moviestest.data.model.MoviesDatabase;
import com.gustavoforero.moviestest.data.model.dao.MovieDao;
import com.gustavoforero.moviestest.data.model.dao.TrailerDao;
import com.gustavoforero.moviestest.data.remote.TheMovieDBApi;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

@Module(includes = ViewModelModule.class)
class AppModule {

    @Provides
    @Singleton
    TheMovieDBApi provideRetrofit() {

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TheMovieDBApi.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(TheMovieDBApi.class);
    }

    @Provides
    @Singleton
    MoviesDatabase provideMovieDatabase(Application application) {
        return Room.databaseBuilder(application, MoviesDatabase.class, "moviesApp.db").build();
    }

    @Provides
    @Singleton
    MovieDao provideMovieDao(MoviesDatabase moviesDatabase) {
        return moviesDatabase.movieDao();
    }

    @Provides
    @Singleton
    TrailerDao provideTrailerDao(MoviesDatabase moviesDatabase) {
        return moviesDatabase.trailerDao();
    }

}
