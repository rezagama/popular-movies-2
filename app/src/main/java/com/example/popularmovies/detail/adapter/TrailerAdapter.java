package com.example.popularmovies.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ItemMovieTrailerBinding;
import com.example.popularmovies.detail.model.MovieTrailer;
import com.example.popularmovies.detail.model.TrailerDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 8/7/17.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.TrailerViewHolder> {
    private List<TrailerDetail> trailers;
    private OnClickListener listener;

    public TrailerAdapter(OnClickListener listener) {
        trailers = new ArrayList<>();
        this.listener = listener;
    }

    @Override
    public TrailerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieTrailerBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_trailer, parent, false);
        return new TrailerViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(TrailerViewHolder holder, int position) {
        TrailerDetail trailer = trailers.get(position);
        holder.setMovieTrailer(trailer);
        holder.setClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public void setTrailers(MovieTrailer movie) {
        if (movie == null) return;
        trailers = movie.trailerList;
        notifyDataSetChanged();
    }

    public static class TrailerViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieTrailerBinding binding;
        private TrailerDetail trailer;

        public TrailerViewHolder(ItemMovieTrailerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setMovieTrailer(TrailerDetail trailer){
            binding.setTitle(trailer.name);
            this.trailer = trailer;
        }

        private void setClickListener(OnClickListener listener){
            binding.getRoot().setOnClickListener(v ->
                    listener.onTrailerClick(trailer)
            );
        }
    }

    public interface OnClickListener {
        void onTrailerClick(TrailerDetail trailer);
    }
}
