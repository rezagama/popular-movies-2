package com.example.popularmovies.database;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by rezagama on 8/7/17.
 */

public class MovieProvider extends ContentProvider {
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private MovieDBHelper movieDBHelper;

    private static final int MOVIE = 0;
    private static final int MOVIE_WITH_ID = 1;

    private static UriMatcher buildUriMatcher(){
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = MovieContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, MovieContract.MovieEntry.MOVIE_TABLE, MOVIE);
        matcher.addURI(authority, MovieContract.MovieEntry.MOVIE_TABLE + "/#", MOVIE_WITH_ID);

        return matcher;
    }

    @Override
    public boolean onCreate(){
        movieDBHelper = new MovieDBHelper(getContext());
        return true;
    }

    @Override
    public String getType(@NonNull Uri uri){
        final int match = sUriMatcher.match(uri);

        switch (match){
            case MOVIE:
                return MovieContract.MovieEntry.CONTENT_DIR_TYPE;
            case MOVIE_WITH_ID:
                return MovieContract.MovieEntry.CONTENT_ITEM_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Cursor query(@NonNull Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        Cursor retCursor;
        switch(sUriMatcher.match(uri)){
            case MOVIE:
                retCursor = movieDBHelper.getReadableDatabase().query(
                        MovieContract.MovieEntry.MOVIE_TABLE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);
                return retCursor;
            case MOVIE_WITH_ID:
                retCursor = movieDBHelper.getReadableDatabase().query(
                        MovieContract.MovieEntry.MOVIE_TABLE,
                        projection,
                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                        new String[] {String.valueOf(ContentUris.parseId(uri))},
                        null,
                        null,
                        sortOrder);
                return retCursor;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
    }

    @Override
    public Uri insert(@NonNull Uri uri, ContentValues values){
        Uri insertUri;
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case MOVIE: {
                long _id = db.insert(MovieContract.MovieEntry.MOVIE_TABLE, null, values);
                if (_id > 0) {
                    insertUri = MovieContract.MovieEntry.buildFlavorsUri(_id);
                } else {
                    throw new android.database.SQLException("Failed to insert row into: " + uri);
                }
                break;
            }

            default: {
                throw new UnsupportedOperationException("Unknown uri: " + uri);

            }
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return insertUri;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        final SQLiteDatabase db = movieDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);
        int deletedMovie;
        switch(match){
            case MOVIE:
                deletedMovie = db.delete(MovieContract.MovieEntry.MOVIE_TABLE, selection, selectionArgs);
                db.execSQL(MovieDBHelper.DELETE_FROM_SEQUENCE_QUERY + "'" +
                        MovieContract.MovieEntry.MOVIE_TABLE + "'");
                break;
            case MOVIE_WITH_ID:
                deletedMovie = db.delete(MovieContract.MovieEntry.MOVIE_TABLE,
                        MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                        new String[]{String.valueOf(ContentUris.parseId(uri))});

                db.execSQL(MovieDBHelper.DELETE_FROM_SEQUENCE_QUERY + "'" +
                        MovieContract.MovieEntry.MOVIE_TABLE + "'");

                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }

        return deletedMovie;
    }
}
