package com.example.popularmovies.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rezagama on 8/7/17.
 */

public class TrailerDetail implements Parcelable {
    public String id;
    @SerializedName("iso_639_1")
    public String iso6391;
    @SerializedName("iso_3166_1")
    public String iso31661;
    public String key;
    public String name;
    public String site;
    public int size;
    public String type;

    private TrailerDetail(Parcel in) {
        id = in.readString();
        key = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(id);
        out.writeString(key);
        out.writeString(name);
    }

    public static final Parcelable.Creator<TrailerDetail> CREATOR = new Parcelable.Creator<TrailerDetail>() {
        @Override
        public TrailerDetail createFromParcel(Parcel source) {
            return new TrailerDetail(source);
        }

        @Override
        public TrailerDetail[] newArray(int size) {
            return new TrailerDetail[size];
        }
    };
}
