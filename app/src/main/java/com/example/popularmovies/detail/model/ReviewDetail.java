package com.example.popularmovies.detail.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rezagama on 8/8/17.
 */

public class ReviewDetail implements Parcelable {
    public String id;
    public String author;
    public String content;
    public String url;

    private ReviewDetail(Parcel in) {
        id = in.readString();
        author = in.readString();
        content = in.readString();
        url = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int i) {
        out.writeString(id);
        out.writeString(author);
        out.writeString(content);
        out.writeString(url);
    }

    public static final Parcelable.Creator<ReviewDetail> CREATOR = new Parcelable.Creator<ReviewDetail>() {
        @Override
        public ReviewDetail createFromParcel(Parcel source) {
            return new ReviewDetail(source);
        }

        @Override
        public ReviewDetail[] newArray(int i) {
            return new ReviewDetail[i];
        }
    };
}
