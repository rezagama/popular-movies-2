package com.example.popularmovies.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rezagama on 6/30/17.
 */

public class Result implements Parcelable {
    @SerializedName("vote_count")
    public int voteCount;
    @SerializedName("id")
    public int id;
    @SerializedName("video")
    public boolean video;
    @SerializedName("vote_average")
    public float voteAverage;
    @SerializedName("title")
    public String title;
    @SerializedName("popularity")
    public float popularity;
    @SerializedName("poster_path")
    public String posterPath;
    @SerializedName("original_language")
    public String originalLanguage;
    @SerializedName("original_title")
    public String originalTitle;
    @SerializedName("genre_ids")
    public List<Integer> genreIds = new ArrayList<>();
    @SerializedName("backdrop_path")
    public String backdropPath;
    @SerializedName("adult")
    public boolean adult;
    @SerializedName("overview")
    public String overview;
    @SerializedName("release_date")
    public String releaseDate;

    private Result(Parcel in) {
        voteCount = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        voteAverage = in.readFloat();
        title = in.readString();
        popularity = in.readFloat();
        posterPath = in.readString();
        originalLanguage = in.readString();
        originalTitle = in.readString();
        genreIds = new ArrayList<>();
        in.readList(genreIds, Integer.class.getClassLoader());
        backdropPath = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        releaseDate = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeInt(voteCount);
        out.writeInt(id);
        out.writeByte((byte) (video ? 1 : 0));
        out.writeFloat(voteAverage);
        out.writeString(title);
        out.writeFloat(popularity);
        out.writeString(posterPath);
        out.writeString(originalLanguage);
        out.writeString(originalTitle);
        out.writeList(genreIds);
        out.writeString(backdropPath);
        out.writeByte((byte) (video ? 1 : 0));
        out.writeString(overview);
        out.writeString(releaseDate);
    }

    public static final Parcelable.Creator<Result> CREATOR = new Parcelable.Creator<Result>() {
        @Override
        public Result createFromParcel(Parcel source) {
            return new Result(source);
        }

        @Override
        public Result[] newArray(int size) {
            return new Result[size];
        }
    };
}
