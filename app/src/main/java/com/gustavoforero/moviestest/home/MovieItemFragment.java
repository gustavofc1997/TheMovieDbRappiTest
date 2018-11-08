package com.gustavoforero.moviestest.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.GridLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gustavoforero.moviestest.BaseFragment;
import com.gustavoforero.moviestest.R;
import com.gustavoforero.moviestest.data.model.entity.Movie;
import com.gustavoforero.moviestest.databinding.FragmentMovieListBinding;
import com.gustavoforero.moviestest.moviedetail.MovieDetailActivity;

import java.util.List;


/**
 * Created by gustavofc97 on 7/11/2018.
 */

public class MovieItemFragment extends BaseFragment<MovieListViewModel, FragmentMovieListBinding> implements MovieItemCallback {


    private List<Movie> mListMoviews;
    private MovieListAdapter mMoviesAdapter;
    private static final String PARAM_TAB_CATEGORY = "param.tab.category";
    private int mTabCategory;

    public static MovieItemFragment newInstance(int category) {
        Bundle args = new Bundle();
        args.putInt(PARAM_TAB_CATEGORY, category);
        MovieItemFragment fragment = new MovieItemFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mTabCategory = getArguments().getInt(PARAM_TAB_CATEGORY);
    }

    @Override
    public Class<MovieListViewModel> getViewModel() {
        return MovieListViewModel.class;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_movie_list;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        dataBinding.rvMovies.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        mMoviesAdapter = new MovieListAdapter(this);
        dataBinding.rvMovies.setAdapter(mMoviesAdapter);
        return dataBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataBinding.txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mMoviesAdapter.searchMoviesByTitle(charSequence.toString(),mListMoviews);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void loadMoviesDefault() {

        switch (mTabCategory) {
            case 0:
                viewModel.getmPopularMovies()
                        .observe(this, listResource -> {
                            mListMoviews = listResource.data;
                            dataBinding.setResource(listResource);
                        });
                break;
            case 1:
                viewModel.getmTopRatedMovies()
                        .observe(this, listResource -> {
                            mListMoviews = listResource.data;
                            dataBinding.setResource(listResource);
                        });
                break;
            case 2:
                viewModel.getmUpcomingMovies()
                        .observe(this, listResource -> {
                            mListMoviews = listResource.data;
                            dataBinding.setResource(listResource);
                        });
                break;
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadMoviesDefault();
    }

    @Override
    public void onClickMovieItem(Movie movie, View sharedView) {
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(getActivity(), sharedView, getString(R.string.shared_image));
        startActivity(MovieDetailActivity.newIntent(getActivity(), movie.getId()), options.toBundle());
    }
}
