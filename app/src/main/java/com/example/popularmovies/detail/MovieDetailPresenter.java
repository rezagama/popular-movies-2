package com.example.popularmovies.detail;

import android.os.Bundle;
import android.view.View;

import com.example.popularmovies.detail.model.MovieReview;
import com.example.popularmovies.detail.model.MovieTrailer;
import com.example.popularmovies.network.NetworkCallback;
import com.example.popularmovies.services.MovieService;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by rezagama on 8/7/17.
 */

public class MovieDetailPresenter {
    private CompositeSubscription subscriptions;
    private MovieDetailViewModel viewModel;
    private MovieDetailView view;
    private MovieService service;

    public MovieDetailPresenter(MovieService service, MovieDetailViewModel viewModel, MovieDetailView view) {
        this.service = service;
        this.viewModel = viewModel;
        this.view = view;
        subscriptions = new CompositeSubscription();
    }

    public void onViewCreated(Bundle savedInstanceState){
        view.onViewCreated(savedInstanceState);
    }

    public void loadMovieDetails(Bundle savedInstanceState){
        if (savedInstanceState != null) {
            view.reloadMovieDetails(savedInstanceState);
        } else {
            view.loadMovieDetails();
        }
    }

    public void getMovieTrailers(int moveId){
        viewModel.setProgressBarVisibility(View.VISIBLE);
        Subscription subscription = service.getMovieTrailers(moveId, new NetworkCallback<MovieTrailer, Throwable>() {
            @Override
            public void onSuccess(MovieTrailer movieTrailer) {
                viewModel.setProgressBarVisibility(View.GONE);
                view.onLoadMovieTrailers(movieTrailer);
            }

            @Override
            public void onError(Throwable error) {
                viewModel.setProgressBarVisibility(View.GONE);
            }

            @Override
            public void onCompleted() {

            }
        });
        subscriptions.add(subscription);
    }

    public void getMovieReviews(int moveId){
        viewModel.setProgressBarVisibility(View.VISIBLE);
        Subscription subscription = service.getMovieReviews(moveId, new NetworkCallback<MovieReview, Throwable>() {
            @Override
            public void onSuccess(MovieReview movieReview) {
                viewModel.setProgressBarVisibility(View.GONE);
                view.onLoadMovieReviews(movieReview);
            }

            @Override
            public void onError(Throwable error) {
                viewModel.setProgressBarVisibility(View.GONE);
            }

            @Override
            public void onCompleted() {

            }
        });
        subscriptions.add(subscription);
    }

    public void onDestroy(){
        subscriptions.clear();
    }
}
