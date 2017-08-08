package com.example.popularmovies.home.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ItemFavoriteMovieBinding;
import com.example.popularmovies.home.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 8/8/17.
 */

public class FavoriteMovieAdapter extends RecyclerView.Adapter<FavoriteMovieAdapter.FavoriteMovieViewHolder> {
    private List<Result> movies;
    private OnClickListener listener;

    public FavoriteMovieAdapter(OnClickListener listener) {
        movies = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public FavoriteMovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemFavoriteMovieBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_favorite_movie, parent, false);
        return new FavoriteMovieViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(FavoriteMovieViewHolder holder, int position) {
        Result result = movies.get(position);
        holder.setFavoriteMovie(result);
        holder.setClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void setFavoriteMovies(List<Result> movies) {
        if (movies == null) return;
        this.movies = movies;
        notifyDataSetChanged();
    }

    public static class FavoriteMovieViewHolder extends RecyclerView.ViewHolder {
        private ItemFavoriteMovieBinding binding;
        private Result movie;

        public FavoriteMovieViewHolder(ItemFavoriteMovieBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setFavoriteMovie(Result movie){
            binding.setTitle(movie.title);
            this.movie = movie;
        }

        private void setClickListener(OnClickListener listener){
            binding.getRoot().setOnClickListener(v ->
                    listener.onFavoriteMovieClick(movie)
            );
        }
    }

    public interface OnClickListener {
        void onFavoriteMovieClick(Result movie);
    }
}
