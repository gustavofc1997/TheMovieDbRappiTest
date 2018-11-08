package com.gustavoforero.moviestest.data.model.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.gustavoforero.moviestest.data.model.entity.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movie WHERE movieCategory=:cat")
    LiveData<List<Movie>> loadMoviesByCat(String cat);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveMovies(List<Movie> movieEntities);

    @Query("SELECT * FROM Movie WHERE id=:id")
    LiveData<Movie> getMovie(int id);

}
