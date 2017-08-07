package com.example.popularmovies.home.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout.*;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ItemMovieGridBinding;
import com.example.popularmovies.home.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieViewHolder> {
    private Context context;
    private List<Result> results;
    private OnClickListener onClickListener;

    public MovieGridAdapter(Context context, OnClickListener onClickListener) {
        this.context = context;
        this.onClickListener = onClickListener;
        results = new ArrayList<>();
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieGridBinding view = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_grid, parent, false);
        return new MovieViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Result result = results.get(position);
        holder.bindData(result.posterPath);
        holder.view.getRoot().setOnClickListener(v ->
                onClickListener.onMovieGridClick(result));
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieGridBinding view;
        private Context context;

        private MovieViewHolder(ItemMovieGridBinding view, Context context) {
            super(view.getRoot());
            this.context = context;
            this.view = view;
        }

        private void bindData(String imgUrl){
            view.setImgUrl(imgUrl);
            setItemHeight();
        }

        private void setItemHeight(){
            int height;
            if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                height = context.getResources().getDimensionPixelSize(R.dimen.dimen_movie_grid_item_height_portrait);
            } else {
                height = context.getResources().getDimensionPixelSize(R.dimen.dimen_movie_grid_item_height_landscape);
            }
            LayoutParams layoutParams = new LayoutParams(LayoutParams.MATCH_PARENT, height);
            view.imgMoviePoster.setLayoutParams(layoutParams);
        }
    }

    public void setResults(List<Result> results) {
        if(results == null) return;
        this.results = results;
        notifyDataSetChanged();
    }

    public interface OnClickListener {
        void onMovieGridClick(Result result);
    }
}
