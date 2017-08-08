package com.example.popularmovies.services;

import com.example.popularmovies.detail.model.MovieReview;
import com.example.popularmovies.detail.model.MovieTrailer;
import com.example.popularmovies.home.model.Movie;
import com.example.popularmovies.network.NetworkCallback;
import com.example.popularmovies.network.NetworkService;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieService {
    private NetworkService service;

    public MovieService(NetworkService service) {
        this.service = service;
    }

    public Subscription getMovieList(String sortBy, NetworkCallback<Movie, Throwable> callback){
        return service.getMovieList(sortBy)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .subscribe(new Subscriber<Movie>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(Movie movie) {
                        callback.onSuccess(movie);
                    }
                });
    }

    public Subscription getMovieTrailers(int movieId, NetworkCallback<MovieTrailer, Throwable> callback){
        return service.getMovieTrailers(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .subscribe(new Subscriber<MovieTrailer>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(MovieTrailer movie) {
                        callback.onSuccess(movie);
                    }
                });
    }

    public Subscription getMovieReviews(int movieId, NetworkCallback<MovieReview, Throwable> callback){
        return service.getMovieReviews(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(Observable::error)
                .subscribe(new Subscriber<MovieReview>() {
                    @Override
                    public void onCompleted() {
                        callback.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(e);
                    }

                    @Override
                    public void onNext(MovieReview movie) {
                        callback.onSuccess(movie);
                    }
                });
    }
}
