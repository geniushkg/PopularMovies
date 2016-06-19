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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        if(savedInstanceState == null){
            MovieEntity movieEntity = Parcels.unwrap(getIntent().getParcelableExtra("movieParcel"));
            MovieDetailFragmentImpView movieDetailView = MovieDetailFragmentImpView.getInstance(movieEntity);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.movie_detail_container_activity, movieDetailView, D_TAG)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.home:

                break;
            default:
                NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(this,"id is : "+item.getItemId(),Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
