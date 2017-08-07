package com.example.popularmovies.home;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityHomeBinding;
import com.example.popularmovies.deps.AppDependenciesProvider;
import com.example.popularmovies.filter.MovieFilterActivity;
import com.example.popularmovies.home.grid.MovieGridFragment;
import com.example.popularmovies.home.model.Movie;
import com.example.popularmovies.services.MovieService;

import javax.inject.Inject;

import static com.example.popularmovies.filter.MovieFilterActivity.SELECTED_OPTION;

public class HomeActivity extends AppCompatActivity implements HomeView {
    private static final String SORT_BY_POPULARITY_KEY = "popular";
    private static final String SORT_BY_TOP_RATED_KEY = "top_rated";
    private static final int SORT_BY_REQUEST_CODE = 1001;

    private HomePresenter presenter;
    private ActivityHomeBinding binding;
    private FragmentManager fragmentManager;

    private String selectedOption;

    @Inject
    MovieService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((AppDependenciesProvider) getApplicationContext()).provideAppDependencies().inject(this);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        binding.setProgressVisibility(View.GONE);
        fragmentManager = getSupportFragmentManager();
        presenter = new HomePresenter(service, this);
        presenter.onViewCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(Bundle savedInstanceState) {
        binding.layoutSortBy.setOnClickListener(view -> navigateToMovieFilter());
        presenter.loadMovieGrid(savedInstanceState);
        setSortByTxt(savedInstanceState);
    }

    private void setSortByTxt(Bundle savedInstanceState){
        if (savedInstanceState == null) {
            selectedOption = getString(R.string.text_popularity);
        } else {
            selectedOption = savedInstanceState.getString(SELECTED_OPTION);
        }
        binding.setSortByTxt(selectedOption);
    }

    @Override
    public void loadMovieGrid() {
        presenter.getMovieList(SORT_BY_POPULARITY_KEY);
    }

    private void navigateToMovieFilter(){
        Intent intent = new Intent(this, MovieFilterActivity.class);
        intent.putExtra(SELECTED_OPTION, selectedOption);
        startActivityForResult(intent, SORT_BY_REQUEST_CODE);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SELECTED_OPTION, selectedOption);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == Activity.RESULT_OK && requestCode == SORT_BY_REQUEST_CODE &&
                !selectedOption.equals(data.getStringExtra(SELECTED_OPTION))){
            selectedOption = data.getStringExtra(SELECTED_OPTION);
            if (selectedOption.equals(getString(R.string.text_popularity))){
                presenter.getMovieList(SORT_BY_POPULARITY_KEY);
            } else {
                presenter.getMovieList(SORT_BY_TOP_RATED_KEY);
            }
            binding.setSortByTxt(selectedOption);
        }
    }

    @Override
    public void resetResults(){
        binding.setProgressVisibility(View.VISIBLE);
        binding.setProgressTxt(getString(R.string.text_please_wait));
        Fragment fragment = fragmentManager.findFragmentById(R.id.layout_movie_grid);
        if (fragment != null){
            fragmentManager.beginTransaction().remove(fragment).commit();
        }
    }

    @Override
    public void onLoadMovieGrid(Movie movie) {
        fragmentManager.beginTransaction().replace(R.id.layout_movie_grid,
                MovieGridFragment.newInstance(movie.results)).commit();
    }

    @Override
    public void showProgressText() {
        binding.setProgressVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressText() {
        binding.setProgressVisibility(View.GONE);
    }

    @Override
    public void onError() {
        binding.setProgressTxt(getString(R.string.text_error));
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }
}
