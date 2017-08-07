package com.example.popularmovies.network;

import android.content.Context;
import android.support.annotation.NonNull;

import com.example.popularmovies.R;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by rezagama on 6/30/17.
 */

public class RequestInterceptor implements Interceptor {
    private static final String API_KEY = "api_key";
    private Context context;

    public RequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request origin = chain.request();
        HttpUrl originUrl = origin.url();

        HttpUrl url = originUrl.newBuilder()
                .addQueryParameter(API_KEY, context.getString(R.string.movie_api_key))
                .build();

        Request.Builder builder = origin.newBuilder()
                .url(url);

        Request request = builder.build();
        return chain.proceed(request);
    }
}
