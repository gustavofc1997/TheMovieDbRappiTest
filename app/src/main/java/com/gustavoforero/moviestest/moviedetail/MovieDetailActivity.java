package com.gustavoforero.moviestest.moviedetail;

import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.gustavoforero.moviestest.R;
import com.gustavoforero.moviestest.databinding.ActivityMovieDetailBinding;
import com.gustavoforero.moviestest.util.AppConstants;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Created by gustavofc97 on 7/11/2018.
 */


public class MovieDetailActivity extends AppCompatActivity implements LifecycleRegistryOwner, YouTubePlayer.PlaybackEventListener, YouTubePlayer.OnInitializedListener {

    private static final String PARAM_MOVIE_ID = "param_movie_id";
    LifecycleRegistry mLfRegistry = new LifecycleRegistry(this);
    ActivityMovieDetailBinding mActivityBinding;
    private String mTrailerId;
    @Inject
    ViewModelProvider.Factory mViewModelFactory;

    MovieDetailViewModel movieDetailViewModel;

    public static Intent newIntent(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(PARAM_MOVIE_ID, movieId);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        movieDetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(MovieDetailViewModel.class);

        int movieId = getIntent().getIntExtra(PARAM_MOVIE_ID, 0);
        movieDetailViewModel.getMovieById(movieId)
                .observe(this, movieEntity -> mActivityBinding.setMovie(movieEntity));
        movieDetailViewModel.getTrailers(String.valueOf(movieId)).observe(this, trailer ->
                {
                    if ((trailer != null ? trailer.data : null) != null)
                        if (!trailer.data.isEmpty()) {
                            mTrailerId = trailer.data.get(0).getKey();
                            initYTplayer();
                        }
                }

        );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                ActivityCompat.finishAfterTransition(this);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initYTplayer() {
        YouTubePlayerSupportFragment mYoutubePlayerFragment = new YouTubePlayerSupportFragment();
        mYoutubePlayerFragment.initialize(AppConstants.DEVELOPER_KEY_YOUTUBE, this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_youtube_player, mYoutubePlayerFragment);
        fragmentTransaction.commit();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLfRegistry;
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean wasRestored) {
        if (!wasRestored) {
            youTubePlayer.cueVideo(mTrailerId);
        }


    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

    @Override
    public void onPlaying() {

    }

    @Override
    public void onPaused() {

    }

    @Override
    public void onStopped() {

    }

    @Override
    public void onBuffering(boolean b) {

    }

    @Override
    public void onSeekTo(int i) {

    }
}
