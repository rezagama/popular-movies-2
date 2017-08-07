package com.example.popularmovies.services;

import com.example.popularmovies.home.model.Movie;
import com.example.popularmovies.network.NetworkCallback;
import com.example.popularmovies.network.NetworkService;

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
}
