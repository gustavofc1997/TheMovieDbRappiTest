package com.gustavoforero.moviestest.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.gustavoforero.moviestest.BaseAdapter;
import com.gustavoforero.moviestest.data.model.entity.Movie;
import com.gustavoforero.moviestest.databinding.ItemMovieListBinding;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gustavofc97 on 7/11/2018.
 */

public class MovieListAdapter extends BaseAdapter<MovieListAdapter.MovieViewHolder, Movie> {

    private List<Movie> mMoviesList;

    private final MovieItemCallback movieItemCallback;

    MovieListAdapter(@NonNull MovieItemCallback movieItemCallback) {
        mMoviesList = new ArrayList<>();
        this.movieItemCallback = movieItemCallback;
    }

    void searchMoviesByTitle(String text, List<Movie> moviesNoFiltered){
        List<Movie> movies=new ArrayList<>();
        for (Movie movieEntity : moviesNoFiltered) {
            if (movieEntity.getTitle().toLowerCase().contains(text)){
                movies.add(movieEntity);
            }
        }
        setData(movies);


    }
    @Override
    public void setData(List<Movie> movieEntities) {
        this.mMoviesList = movieEntities;
        notifyDataSetChanged();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return MovieViewHolder.create(LayoutInflater.from(viewGroup.getContext()), viewGroup, movieItemCallback);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, int i) {
        viewHolder.onBind(mMoviesList.get(i));
    }

    @Override
    public int getItemCount() {
        return mMoviesList.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {

        public static MovieViewHolder create(LayoutInflater inflater, ViewGroup parent, MovieItemCallback callback) {
            ItemMovieListBinding itemMovieListBinding = ItemMovieListBinding.inflate(inflater, parent, false);
            return new MovieViewHolder(itemMovieListBinding, callback);
        }

        ItemMovieListBinding binding;

        MovieViewHolder(ItemMovieListBinding binding, MovieItemCallback callback) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(v ->
                    callback.onClickMovieItem(binding.getMovie(), binding.imgPoster));
        }

        void onBind(Movie movie) {
            binding.setMovie(movie);
            binding.executePendingBindings();
        }
    }
}
