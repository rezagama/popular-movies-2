package com.example.popularmovies.home.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 6/30/17.
 */

public class Movie {
    @SerializedName("page")
    public int page;
    @SerializedName("total_results")
    public int totalResult;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("results")
    public List<Result> results = new ArrayList<>();
}
