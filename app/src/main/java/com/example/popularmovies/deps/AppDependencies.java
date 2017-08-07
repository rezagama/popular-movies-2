package com.example.popularmovies.deps;

import com.example.popularmovies.MovieApp;
import com.example.popularmovies.detail.MovieDetailActivity;
import com.example.popularmovies.home.HomeActivity;
import com.example.popularmovies.common.AppModule;
import com.example.popularmovies.network.NetworkModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by rezagama on 6/30/17.
 */

@Singleton
@Component (modules = {
        AppModule.class,
        NetworkModule.class
})

public interface AppDependencies {
    void inject(MovieApp movieApp);

    void inject(HomeActivity homeActivity);

    void inject(MovieDetailActivity movieDetailActivity);
}
