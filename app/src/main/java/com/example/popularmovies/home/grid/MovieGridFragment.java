package com.example.popularmovies.home.grid;

import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmovies.R;
import com.example.popularmovies.database.MovieContract;
import com.example.popularmovies.databinding.FragmentMovieGridBinding;
import com.example.popularmovies.detail.MovieDetailActivity;
import com.example.popularmovies.home.adapter.FavoriteMovieAdapter;
import com.example.popularmovies.home.adapter.MovieGridAdapter;
import com.example.popularmovies.home.model.Result;

import java.util.ArrayList;
import java.util.List;

import static com.example.popularmovies.detail.MovieDetailActivity.MOVIE_DATA;

/**
 * Created by rezagama on 7/2/17.
 */

public class MovieGridFragment extends Fragment {
    private static final String SELECTED_OPTION = "option";

    private static final int MOVIE_GRID = 0;
    private static final int FAVORITE_LIST = 1;
    private static final int PORTRAIT_GRID_COLUMN = 2;
    private static final int LANDSCAPE_GRID_COLUMN = 4;

    private MovieGridAdapter adapter;
    private FavoriteMovieAdapter favoriteMovieAdapter;
    private FragmentMovieGridBinding binding;
    private GridLayoutManager layoutManager;
    private List<Result> results;
    private int selectedOption;

    public static MovieGridFragment newInstance(List<Result> result) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(MOVIE_DATA, (ArrayList<? extends Parcelable>) result);
        MovieGridFragment fragment = new MovieGridFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        results = getArguments().getParcelableArrayList(MOVIE_DATA);
        setSelectedOption(savedInstanceState);
        setMovieListLayout();
    }

    private void setSelectedOption(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            selectedOption = savedInstanceState.getInt(SELECTED_OPTION);
        }
    }

    private void setMovieListLayout() {
        if (isMovieGrid()){
            setMovieGridLayout();
        } else {
            setFavoriteListLayout();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_grid, container, false);
        binding.setNotificationVisibility(View.GONE);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(isMovieGrid()){
            binding.listMovieGrid.setLayoutManager(layoutManager);
            binding.listMovieGrid.setAdapter(adapter);
            adapter.setResults(results);
        } else {
            loadFavoriteMovies();
            setFavoriteMovies();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SELECTED_OPTION, selectedOption);
    }

    private void setFavoriteMovies() {
        binding.listMovieGrid.setAdapter(favoriteMovieAdapter);
        favoriteMovieAdapter.setFavoriteMovies(results);
    }

    private void loadFavoriteMovies() {
        results.clear();
        binding.listMovieGrid.setLayoutManager(new LinearLayoutManager(getContext()));
        Cursor cursor = getActivity().getContentResolver().query(MovieContract.MovieEntry.CONTENT_URI,
                        null,
                        null,
                        null,
                        null);

        if(cursor != null && cursor.moveToFirst()){
            do {
                Result movie = new Result();

                int idIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_ID);
                int titleIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE);
                int dateIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE);
                int voteIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_AVERAGE_VOTE);
                int synopsisIndex = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_MOVIE_SYNOPSIS);

                movie.id = cursor.getInt(idIndex);
                movie.title = cursor.getString(titleIndex);
                movie.releaseDate = cursor.getString(dateIndex);
                movie.voteAverage = cursor.getFloat(voteIndex);
                movie.overview = cursor.getString(synopsisIndex);

                results.add(movie);
            } while (cursor.moveToNext());

            cursor.close();
        } else {
            binding.setNotificationVisibility(View.VISIBLE);
        }
    }

    private void setMovieGridLayout(){
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            layoutManager = new GridLayoutManager(getContext(), PORTRAIT_GRID_COLUMN);
        } else {
            layoutManager = new GridLayoutManager(getContext(), LANDSCAPE_GRID_COLUMN);
        }
        adapter = new MovieGridAdapter(getContext(), this::navigateToMovieDetail);
    }

    private void setFavoriteListLayout(){
        favoriteMovieAdapter = new FavoriteMovieAdapter(this::navigateToMovieDetail);
        selectedOption = FAVORITE_LIST;
    }

    private void navigateToMovieDetail(Result result){
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MOVIE_DATA, result);
        startActivity(intent);
    }

    private boolean isMovieGrid(){
        return results.size() > 0 && selectedOption == MOVIE_GRID;
    }
}
