package com.example.popularmovies.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rezagama on 8/7/17.
 */

public class MovieDBHelper extends SQLiteOpenHelper {
    public static final String DELETE_FROM_SEQUENCE_QUERY = "DELETE FROM SQLITE_SEQUENCE WHERE NAME = ";
    private static final String DROP_TABLE_QUERY = "DROP TABLE IF EXISTS ";
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public MovieDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " +
                MovieContract.MovieEntry.MOVIE_TABLE + "(" +
                MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_TITLE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE + " TEXT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_AVERAGE_VOTE + " FLOAT NOT NULL, " +
                MovieContract.MovieEntry.COLUMN_MOVIE_SYNOPSIS + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DROP_TABLE_QUERY + MovieContract.MovieEntry.MOVIE_TABLE);
        sqLiteDatabase.execSQL(DELETE_FROM_SEQUENCE_QUERY + "'" + MovieContract.MovieEntry.MOVIE_TABLE + "'");

        onCreate(sqLiteDatabase);
    }
}
