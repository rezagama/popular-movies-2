package com.example.popularmovies.detail;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Menu;
import android.view.MenuItem;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityMovieDetailBinding;
import com.example.popularmovies.deps.AppDependenciesProvider;
import com.example.popularmovies.detail.adapter.TrailerAdapter;
import com.example.popularmovies.detail.model.MovieTrailer;
import com.example.popularmovies.home.model.Result;
import com.example.popularmovies.services.MovieService;

import javax.inject.Inject;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailView {
    public final static String MOVIE_DATA = "movie_data";
    public final static String MOVIE_TRAILER = "movie_list";

    private final static String YOUTUBE_PACKAGE_NAME = "com.google.android.youtube";
    private final static String YOUTUBE_URI = "http://www.youtube.com/watch?v=";

    private ActivityMovieDetailBinding binding;
    private MovieDetailPresenter presenter;
    private MovieDetailViewModel viewModel;
    private TrailerAdapter trailerAdapter;
    private MovieTrailer movieTrailer;
    private Result movie;

    @Inject
    MovieService movieService;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppDependenciesProvider) getApplicationContext()).provideAppDependencies().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        movie = getIntent().getParcelableExtra(MOVIE_DATA);
        viewModel = new MovieDetailViewModel(movie);
        presenter = new MovieDetailPresenter(movieService, viewModel, this);
        binding.setViewModel(viewModel);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.text_movie_detail));
        }

        presenter.onViewCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        binding.listMovieTrailers.setLayoutManager(new LinearLayoutManager(this));
        trailerAdapter = new TrailerAdapter(trailer -> {
            Intent intent;
            String trailerURI = YOUTUBE_URI + trailer.key;
            if(isYotubeInstalled()) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerURI));
            } else {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(trailerURI));
            }
            startActivity(intent);
        });
        binding.listMovieTrailers.setAdapter(trailerAdapter);
        presenter.loadMovieTrailers(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(MOVIE_TRAILER, movieTrailer);
        outState.putParcelable(MOVIE_DATA, movie);
    }

    private boolean isYotubeInstalled(){
        try {
            getPackageManager().getPackageInfo(YOUTUBE_PACKAGE_NAME, 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    @Override
    public void loadMovieTrailers() {
        presenter.getMovieTrailers(movie.id);
    }

    @Override
    public void reloadMovieTrailers(Bundle savedInstanceState) {
        movieTrailer = savedInstanceState.getParcelable(MOVIE_TRAILER);
        trailerAdapter.setTrailers(movieTrailer);
    }

    @Override
    public void onLoadMovieTrailers(MovieTrailer movieTrailer) {
        trailerAdapter.setTrailers(movieTrailer);
        this.movieTrailer = movieTrailer;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }
}
