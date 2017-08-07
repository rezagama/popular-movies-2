package com.example.popularmovies.filter;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.popularmovies.R;
import com.example.popularmovies.databinding.ActivityMovieFilterBinding;

/**
 * Created by rezagama on 7/2/17.
 */

public class MovieFilterActivity extends AppCompatActivity {
    public static final String SELECTED_OPTION = "selected_option";

    private ActivityMovieFilterBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String [] options = getResources().getStringArray(R.array.sort_by_options);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_filter);

        binding.listMovieFilter.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, options) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView textView = (TextView) super.getView(position, convertView, parent);

                String currentOption = getIntent().getStringExtra(SELECTED_OPTION);
                int textColor = textView.getText().toString().equals(currentOption) ?
                        android.R.color.secondary_text_dark : android.R.color.primary_text_light;
                textView.setTextColor(ContextCompat.getColor(getApplicationContext(), textColor));

                return textView;
            }
        });

        binding.listMovieFilter.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent();
            String selectedOption = (String) (binding.listMovieFilter.getItemAtPosition(i));
            intent.putExtra(SELECTED_OPTION, selectedOption);
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(getString(R.string.text_sort_by_title));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        binding.unbind();
        super.onDestroy();
    }
}
