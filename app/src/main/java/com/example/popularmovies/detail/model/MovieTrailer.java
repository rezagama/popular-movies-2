package com.example.popularmovies.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 8/7/17.
 */

public class MovieTrailer implements Parcelable {
    public int id;
    @SerializedName("results")
    public List<TrailerDetail> trailerList;

    private MovieTrailer(Parcel in) {
        id = in.readInt();
        trailerList = new ArrayList<>();
        in.readList(trailerList, TrailerDetail.class.getClassLoader());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(id);
        out.writeList(trailerList);
    }

    public static final Parcelable.Creator<MovieTrailer> CREATOR = new Parcelable.Creator<MovieTrailer>() {
        @Override
        public MovieTrailer createFromParcel(Parcel source) {
            return new MovieTrailer(source);
        }

        @Override
        public MovieTrailer[] newArray(int size) {
            return new MovieTrailer[size];
        }
    };
}
