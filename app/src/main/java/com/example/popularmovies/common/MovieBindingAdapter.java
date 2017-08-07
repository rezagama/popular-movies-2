package com.example.popularmovies.common;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.popularmovies.R;

/**
 * Created by rezagama on 6/30/17.
 */

public final class MovieBindingAdapter {
    @BindingAdapter("bind:imgUrl")
    public static void loadImage(ImageView imageView, String imgUrl){
        RequestOptions options = new RequestOptions().fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);

        Glide.with(imageView.getContext())
                .load(imageView.getContext()
                        .getString(R.string.image_url, imgUrl))
                .apply(options)
                .into(imageView);
    }
}
