package com.example.popularmovies.home;

import android.os.Bundle;

import com.example.popularmovies.home.model.Movie;

/**
 * Created by rezagama on 6/30/17.
 */

public interface HomeView {
    void onViewCreated(Bundle savedInstanceState);

    void loadMovieGrid();

    void onLoadMovieGrid(Movie movie);

    void loadFavoritesMovie();

    void showProgressText();

    void hideProgressText();

    void resetResults();

    void onError();
}
