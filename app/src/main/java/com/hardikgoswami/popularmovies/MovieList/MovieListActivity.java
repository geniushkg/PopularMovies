package com.hardikgoswami.popularmovies.MovieList;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.MovieDetail.MovieDetailActivity;
import com.hardikgoswami.popularmovies.MovieDetail.MovieDetailFragmentImpView;
import com.hardikgoswami.popularmovies.R;

public class MovieListActivity extends AppCompatActivity  {
    private boolean mIsDualPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View detailView = findViewById(R.id.movies_detail_container);
        mIsDualPane = detailView != null &&
                detailView.getVisibility() == View.VISIBLE;
        Toast.makeText(this,"misDual pane : "+mIsDualPane,Toast.LENGTH_SHORT).show();
        if(mIsDualPane){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movies_grid_container,new MovieListFragmentImplView())
                    .commit();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movies_detail_container,new MovieDetailFragmentImpView())
                    .commit();
        }else {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.single_movies_list_container,new MovieListFragmentImplView())
                    .commit();
        }
    }

}
