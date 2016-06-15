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
    MovieEntity movieEntity;
    MovieDetailFragmentImpView movieDetailFragmentImpView = new MovieDetailFragmentImpView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        movieEntity = (MovieEntity)  Parcels.unwrap(getIntent().getParcelableExtra("movieParcel"));
        Bundle data = new Bundle();
        data.putInt("detailActivityState",1);
        movieDetailFragmentImpView.setArguments(data);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.movie_detail_container_activity,movieDetailFragmentImpView)
                .commit();

    }
    public MovieEntity getMovieEntity(){
        return movieEntity;
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
