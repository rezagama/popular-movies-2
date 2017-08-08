package com.example.popularmovies.detail;

import android.os.Bundle;

import com.example.popularmovies.detail.model.MovieReview;
import com.example.popularmovies.detail.model.MovieTrailer;

/**
 * Created by rezagama on 8/7/17.
 */

public interface MovieDetailView {
    void onViewCreated(Bundle savedInstanceState);

    void reloadMovieDetails(Bundle savedInstanceState);

    void onLoadMovieTrailers(MovieTrailer movieTrailer);

    void onLoadMovieReviews(MovieReview movieReview);

    void loadMovieDetails();
}
