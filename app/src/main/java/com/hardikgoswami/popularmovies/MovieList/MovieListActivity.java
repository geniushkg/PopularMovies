package com.hardikgoswami.popularmovies.movielist;

import com.hardikgoswami.popularmovies.R;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.hardikgoswami.popularmovies.moviedetail.MovieDetailActivity;
import com.hardikgoswami.popularmovies.moviedetail.MovieDetailFragmentImpView;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import org.parceler.Parcels;

public class MovieListActivity extends AppCompatActivity {
    private boolean mIsDualPane;
    public static final String D_TAG = "DETAIL_FRAG";
    MovieDetailFragmentImpView movieDetailFragmentImpView = new MovieDetailFragmentImpView();
    MovieListFragmentImplView movieListFragmentImplView = new MovieListFragmentImplView();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View detailView = findViewById(R.id.movies_detail_container);
        mIsDualPane = false;
        if (detailView != null &&
                detailView.getVisibility() == View.VISIBLE) {
            mIsDualPane = true;
        }

        if (mIsDualPane) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movies_grid_container, movieListFragmentImplView)
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movies_detail_container, movieDetailFragmentImpView, D_TAG)
                    .commit();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.single_movies_list_container, movieListFragmentImplView)
                    .commit();

        }
    }

    public void updateUi(MovieEntity parcelData) {
        if (mIsDualPane) {
            MovieDetailFragmentImpView movieFragment = (MovieDetailFragmentImpView) getSupportFragmentManager().findFragmentByTag(D_TAG);
            movieFragment.displayMovieData(parcelData);
        } else {
            Intent intent = new Intent(this, MovieDetailActivity.class);
            intent.putExtra("movieParcel", Parcels.wrap(parcelData));
            startActivity(intent);
        }
    }

    public boolean isDualPane() {
        return mIsDualPane;
    }

}
