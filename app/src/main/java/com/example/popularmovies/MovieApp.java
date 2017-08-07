package com.example.popularmovies;

import android.support.multidex.MultiDexApplication;

import com.example.popularmovies.common.AppModule;
import com.example.popularmovies.deps.AppDependencies;
import com.example.popularmovies.deps.AppDependenciesProvider;
import com.example.popularmovies.deps.DaggerAppDependencies;
import com.example.popularmovies.network.NetworkModule;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieApp extends MultiDexApplication implements AppDependenciesProvider {
    private AppDependencies appDependencies;

    @Override
    public void onCreate() {
        super.onCreate();
        appDependencies = DaggerAppDependencies.builder().appModule(new AppModule(this))
                .networkModule(new NetworkModule(getString(R.string.server_url, getString(R.string.server_ver))))
                .build();
        appDependencies.inject(this);
    }

    @Override
    public AppDependencies provideAppDependencies() {
        return appDependencies;
    }
}
