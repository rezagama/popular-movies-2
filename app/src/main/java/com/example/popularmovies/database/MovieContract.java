package com.example.popularmovies.database;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by rezagama on 8/7/17.
 */

public class MovieContract {
    public static final String CONTENT_AUTHORITY = "com.example.popularmovie.app";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final class MovieEntry implements BaseColumns {
        public static final String MOVIE_TABLE = "movies";

        public static final String _ID = "_id";
        public static final String COLUMN_MOVIE_ID = "movie_id";
        public static final String COLUMN_MOVIE_TITLE = "title";
        public static final String COLUMN_MOVIE_RELEASE_DATE = "release_date";
        public static final String COLUMN_MOVIE_AVERAGE_VOTE = "average_vote";
        public static final String COLUMN_MOVIE_SYNOPSIS = "synopsis";

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(MOVIE_TABLE).build();
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_TABLE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + MOVIE_TABLE;

        public static Uri buildFlavorsUri(long id){
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }
    }
}
