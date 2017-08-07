package com.example.popularmovies.home.grid;

import android.content.Intent;
import android.content.res.Configuration;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.FragmentMovieGridBinding;
import com.example.popularmovies.detail.MovieDetailActivity;
import com.example.popularmovies.home.adapter.MovieGridAdapter;
import com.example.popularmovies.home.model.Result;

import java.util.ArrayList;
import java.util.List;

import static com.example.popularmovies.detail.MovieDetailActivity.MOVIE_DATA;

/**
 * Created by rezagama on 7/2/17.
 */

public class MovieGridFragment extends Fragment {
    private static final int PORTRAIT_GRID_COLUMN = 2;
    private static final int LANDSCAPE_GRID_COLUMN = 4;

    private MovieGridAdapter adapter;
    private FragmentMovieGridBinding binding;
    private GridLayoutManager layoutManager;
    private List<Result> results;

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
        adapter = new MovieGridAdapter(getContext(), this::navigateToMovieDetail);
        setGridLayoutColumn();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_grid, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.listMovieGrid.setLayoutManager(layoutManager);
        binding.listMovieGrid.setAdapter(adapter);
        adapter.setResults(results);
    }

    private void setGridLayoutColumn(){
        if(getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            layoutManager = new GridLayoutManager(getContext(), PORTRAIT_GRID_COLUMN);
        } else {
            layoutManager = new GridLayoutManager(getContext(), LANDSCAPE_GRID_COLUMN);
        }
    }

    private void navigateToMovieDetail(Result result){
        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        intent.putExtra(MOVIE_DATA, result);
        startActivity(intent);
    }
}
