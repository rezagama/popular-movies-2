package com.example.popularmovies.detail;

import android.databinding.BaseObservable;

import com.example.popularmovies.home.model.Result;

/**
 * Created by rezagama on 6/30/17.
 */

public class MovieDetailViewModel extends BaseObservable {
    private Result result;

    public MovieDetailViewModel(Result result) {
        this.result = result;
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
}
