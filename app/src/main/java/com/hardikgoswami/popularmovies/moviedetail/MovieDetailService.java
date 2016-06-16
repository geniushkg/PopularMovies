package com.hardikgoswami.popularmovies.moviedetail;

import com.hardikgoswami.popularmovies.PopularMovieApplication;
import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieReviewResult;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailerResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by geniushkg on 6/16/2016.
 */
public class MovieDetailService implements MovieDetailServieInterface {

    @Override
    public void fetchReviewsFromSource(int movieId, final IListener<List<MovieReview>> callback) {
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
                callback.onFailure("failed : " + t.getMessage());
            }
        });
    }

    @Override
    public void fetchTrailerFromSource(int movieId, final IListener<List<MovieTrailer>> callback) {
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
                callback.onFailure("failed : " + t.getMessage());
            }
        });
    }
}
