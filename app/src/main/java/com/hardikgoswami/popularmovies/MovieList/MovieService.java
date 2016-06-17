package com.hardikgoswami.popularmovies.movielist;

import android.content.Context;
import android.database.Cursor;

import com.hardikgoswami.popularmovies.PopularMovieApplication;
import com.hardikgoswami.popularmovies.util.db.MovieContract;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.hardikgoswami.popularmovies.util.entity.RestResult;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geniushkg on 6/12/2016.
 */
public class MovieService implements MovieServiceInterface {

    public MovieService() {

    }

    @Override
    public void fetchMoviesFromSource(String filter, final IListener<List<MovieEntity>> callBack) {

        if (filter.equalsIgnoreCase("favourite")) {


        } else if (filter.equalsIgnoreCase("popular")) {

            Call<RestResult> movieEntityCall = PopularMovieApplication.getsService().getPopularMovies(PopularMovieApplication.TMDB_API_KEY);
            movieEntityCall.enqueue(new Callback<RestResult>() {
                @Override
                public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                    if (response.isSuccessful()) {
                        List<MovieEntity> movieEntities = response.body().getResults();
                        callBack.onSuccess(movieEntities);
                    } else {
                        callBack.onFailure("response not sucess");
                    }
                }

                @Override
                public void onFailure(Call<RestResult> call, Throwable t) {
                    //callBack.onFailure("onFailure message : " + t.getMessage());
                }
            });

        } else {

            Call<RestResult> movieEntityCall = PopularMovieApplication.getsService().getTopRatedMovies(PopularMovieApplication.TMDB_API_KEY);
            movieEntityCall.enqueue(new Callback<RestResult>() {
                @Override
                public void onResponse(Call<RestResult> call, Response<RestResult> response) {
                    if (response.isSuccessful()) {
                        List<MovieEntity> movieEntities = response.body().getResults();
                        callBack.onSuccess(movieEntities);
                    } else {
                        callBack.onFailure("response not sucess");
                    }
                }

                @Override
                public void onFailure(Call<RestResult> call, Throwable t) {
                    //callBack.onFailure("onFailure message : " + t.getMessage());
                }
            });
        }
    }

    @Override
    public void fetchFavouriteMovies(IListener<List<MovieEntity>> callBack, Context context) {
        String[] colomns = new String[]{"id", "vote_avg", "plot_movie", "title_movie", "release_date_movie", "poster_movie"};
        Cursor mCursor = context.getContentResolver()
                .query(MovieContract.CONTENT_URI, colomns, null, null, null);
        List<MovieEntity> movieEntitiesList = new ArrayList<>();
        try {
            for (mCursor.moveToFirst(); !mCursor.isAfterLast(); mCursor.moveToNext()) {
                MovieEntity movieEntity = new MovieEntity();
                movieEntity.setId(mCursor.getInt(0));
                movieEntity.setVote_average(Double.valueOf(mCursor.getString(1)));
                movieEntity.setOverview(mCursor.getString(2));
                movieEntity.setTitle(mCursor.getString(3));
                movieEntity.setRelease_date(mCursor.getString(4));
                movieEntity.setPoster_blob(mCursor.getBlob(5));
                movieEntitiesList.add(movieEntity);
            }
        } catch (Exception exp) {
            callBack.onFailure("exception occured : " + exp.getMessage());
        } finally {
            mCursor.close();
            if (movieEntitiesList.size() != 0) {
                callBack.onSuccess(movieEntitiesList);
                callBack.onFailure("Sucess Offline movies loaded : " + movieEntitiesList.size());
            }else {
                callBack.onFailure("No Favourite Movies");
            }

        }
    }
}
