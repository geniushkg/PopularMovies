package com.hardikgoswami.popularmovies.moviedetail;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Log;

import com.hardikgoswami.popularmovies.PopularMovieApplication;
import com.hardikgoswami.popularmovies.movielist.IListener;
import com.hardikgoswami.popularmovies.util.db.DbBitmapUtility;
import com.hardikgoswami.popularmovies.util.db.MovieContract;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieReviewResult;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailerResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailService implements MovieDetailServieInterface {
    public static final String TAG = MovieDetailService.class.getSimpleName();

    @Override
    public void fetchReviewsFromSource(int movieId, final IListenerReviews<List<MovieReview>> callback) {
        Call<MovieReviewResult> movieReviewResultCall = PopularMovieApplication
                .getsService()
                .getReviewsOfMovie(movieId, PopularMovieApplication.TMDB_API_KEY);
        movieReviewResultCall.enqueue(new Callback<MovieReviewResult>() {
            @Override
            public void onResponse(Call<MovieReviewResult> call, Response<MovieReviewResult> response) {
                if (response.isSuccessful()) {
                    List<MovieReview> movieReviewList = response.body().getResults();
                    callback.onSuccessReviews(movieReviewList);
                } else {
                    callback.onFailure("failed : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieReviewResult> call, Throwable t) {
                //   callback.onFailure("failed : " + t.getMessage());
            }
        });
    }

    @Override
    public void fetchTrailerFromSource(int movieId, final IListenerTrailers<List<MovieTrailer>> callback) {
        Call<MovieTrailerResult> movieTrailerResultCall = PopularMovieApplication
                .getsService()
                .getTrailersOfMovie(movieId, PopularMovieApplication.TMDB_API_KEY);
        movieTrailerResultCall.enqueue(new Callback<MovieTrailerResult>() {
            @Override
            public void onResponse(Call<MovieTrailerResult> call, Response<MovieTrailerResult> response) {
                if (response.isSuccessful()) {
                    List<MovieTrailer> movieTrailerList = response.body().getResults();
                    callback.onSuccessTrailers(movieTrailerList);
                } else {
                    callback.onFailure("failed : " + response.message());
                }
            }

            @Override
            public void onFailure(Call<MovieTrailerResult> call, Throwable t) {
                // callback.onFailure("failed : " + t.getMessage());
            }
        });
    }


    @Override
    public void storeMovieToDb(MovieEntity favouriteMovie, Bitmap poster, Context context, IListener callback) {
        if (!isFavAlreadyMovie(favouriteMovie, context)) {
            ContentValues values = new ContentValues();
            values.put(MovieContract.MOVIE_ID, favouriteMovie.getId());
            values.put(MovieContract.TITLE_MOVIE, favouriteMovie.getTitle());
            values.put(MovieContract.RELEASE_DATE, favouriteMovie.getRelease_date());
            values.put(MovieContract.PLOT_MOVIE, favouriteMovie.getOverview());
            values.put(MovieContract.VOTE_AVG_COLOUMN, favouriteMovie.getVote_average());
            values.put(MovieContract.POSTER_MOVIE, DbBitmapUtility.getBytes(poster));
            context.getContentResolver().insert(MovieContract.CONTENT_URI, values);
            callback.onFailure("Added to Favourite");
        } else {
            callback.onFailure("Already Favourite");
        }
    }

    boolean isFavAlreadyMovie(MovieEntity favouriteMovie, Context context) {
        String[] column = new String[]{"title_movie"};
        String titleToBeSearch = favouriteMovie.getTitle();
        Cursor c = context.getContentResolver()
                .query(MovieContract.CONTENT_URI, column, "title_movie = ?", new String[]{titleToBeSearch}, null);
        assert c != null;
        return c.getCount() > 0;
    }


}

