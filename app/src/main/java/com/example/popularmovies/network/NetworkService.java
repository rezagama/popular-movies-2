package com.example.popularmovies.network;

import com.example.popularmovies.detail.model.MovieReview;
import com.example.popularmovies.detail.model.MovieTrailer;
import com.example.popularmovies.home.model.Movie;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by rezagama on 6/30/17.
 */

public interface NetworkService {
    @GET("movie/{sort_by}")
    Observable<Movie> getMovieList(@Path("sort_by") String sortBy);
    @GET("movie/{id}/videos")
    Observable<MovieTrailer> getMovieTrailers(@Path("id") int movieId);
    @GET("movie/{id}/reviews")
    Observable<MovieReview> getMovieReviews(@Path("id") int movieId);
}
