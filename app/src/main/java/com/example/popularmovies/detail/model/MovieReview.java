package com.example.popularmovies.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 8/8/17.
 */

public class MovieReview implements Parcelable {
    public int id;
    public int page;
    @SerializedName("results")
    public List<ReviewDetail> reviews;
    @SerializedName("total_pages")
    public int totalPages;
    @SerializedName("total_results")
    public int totalResults;

    private MovieReview(Parcel in) {
        id = in.readInt();
        reviews = new ArrayList<>();
        in.readList(reviews, ReviewDetail.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeList(reviews);
    }

    public static final Parcelable.Creator<MovieReview> CREATOR = new Parcelable.Creator<MovieReview>() {
        @Override
        public MovieReview createFromParcel(Parcel source) {
            return new MovieReview(source);
        }

        @Override
        public MovieReview[] newArray(int size) {
            return new MovieReview[size];
        }
    };
}
