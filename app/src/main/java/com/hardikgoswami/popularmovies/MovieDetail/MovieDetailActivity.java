package com.hardikgoswami.popularmovies.moviedetail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import org.parceler.Parcels;

public class MovieDetailActivity extends AppCompatActivity {
    MovieEntity movieEntity;
    MovieDetailFragmentImpView movieDetailFragmentImpView = new MovieDetailFragmentImpView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        movieEntity = (MovieEntity)  Parcels.unwrap(getIntent().getParcelableExtra("movieParcel"));
        Bundle args = new Bundle();
        args.putInt("detailActivity",1);
        movieDetailFragmentImpView.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.movie_detail_container_activity,movieDetailFragmentImpView)
                .commit();
    }
    public MovieEntity getMovieEntity(){
        return movieEntity;
    }
}
