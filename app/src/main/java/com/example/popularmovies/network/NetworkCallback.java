package com.example.popularmovies.network;

/**
 * Created by rezagama on 6/30/17.
 */

public interface NetworkCallback<R, E> {
    void onSuccess(R response);

    void onError(E error);

    void onCompleted();
}
