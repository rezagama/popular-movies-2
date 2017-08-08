package com.example.popularmovies.detail.adapter;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ItemMovieReviewBinding;
import com.example.popularmovies.detail.model.MovieReview;
import com.example.popularmovies.detail.model.ReviewDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 8/8/17.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder> {
    private List<ReviewDetail> reviews;

    public ReviewAdapter() {
        reviews = new ArrayList<>();
    }

    @Override
    public ReviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemMovieReviewBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.item_movie_review, parent, false);
        return new ReviewViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ReviewViewHolder holder, int position) {
        ReviewDetail review = reviews.get(position);
        holder.setMovieReview(review);
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public void setReviews(MovieReview movie) {
        if (movie == null) return;
        reviews = movie.reviews;
        notifyDataSetChanged();
    }

    public static class ReviewViewHolder extends RecyclerView.ViewHolder {
        private ItemMovieReviewBinding binding;

        public ReviewViewHolder(ItemMovieReviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void setMovieReview(ReviewDetail review){
            binding.setName(review.author);
            binding.setReview(review.content);
        }
    }
}
