package com.example.popularmovies.detail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.View;

import com.example.popularmovies.BR;
import com.example.popularmovies.home.model.Result;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieDetailViewModel extends BaseObservable {
    private Result result;
    private String favoriteBtnText;
    private int progressBarVisibility;

    public MovieDetailViewModel(Result result) {
        this.result = result;
        progressBarVisibility = View.GONE;
    }

    public String getMovieTitle(){
        return result.title;
    }

    public String getMovieSynopsis(){
        return result.overview;
    }

    public String getMoviePoster(){
        return result.posterPath;
    }

    public String getMovieReleaseDate(){
        return result.releaseDate;
    }

    public String getMovieVote(){
        return String.valueOf(result.voteAverage);
    }

    @Bindable
    public int getProgressBarVisibility() {
        return progressBarVisibility;
    }

    public void setProgressBarVisibility(int progressBarVisibility) {
        this.progressBarVisibility = progressBarVisibility;
        notifyPropertyChanged(BR.progressBarVisibility);
    }

    @Bindable
    public String getFavoriteBtnText() {
        return favoriteBtnText;
    }

    public void setFavoriteBtnText(String favoriteBtnText) {
        this.favoriteBtnText = favoriteBtnText;
        notifyPropertyChanged(BR.favoriteBtnText);
    }
}
