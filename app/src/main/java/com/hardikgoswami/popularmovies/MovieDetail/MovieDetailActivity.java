package com.hardikgoswami.popularmovies.moviedetail;

import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import org.parceler.Parcels;

public class MovieDetailActivity extends AppCompatActivity {
    public static final String D_TAG = "DETAIL_FRAG";
    MovieEntity movieEntity;
    MovieDetailFragmentImpView movieDetailFragmentImpView = new MovieDetailFragmentImpView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if(savedInstanceState == null){
            movieEntity = (MovieEntity)  Parcels.unwrap(getIntent().getParcelableExtra("movieParcel"));
            movieDetailFragmentImpView.setParceldata(movieEntity);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.movie_detail_container_activity,movieDetailFragmentImpView,D_TAG)
                    .commit();

        }else {
            movieDetailFragmentImpView = (MovieDetailFragmentImpView) getSupportFragmentManager().findFragmentByTag(D_TAG);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                break;
            default:
                Toast.makeText(this,"up button pressed",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

}
