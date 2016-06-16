package com.hardikgoswami.popularmovies.util.net;

import com.hardikgoswami.popularmovies.util.entity.MovieReviewResult;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailerResult;
import com.hardikgoswami.popularmovies.util.entity.RestResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by geniushkg on 6/12/2016.
 */

public interface TheMovieDbService {
    @GET("movie/popular")
    Call<RestResult> getPopularMovies(@Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<RestResult> getTopRatedMovies(@Query("api_key") String api_key);

    @GET("movie/{id}/videos")
    Call<MovieTrailerResult> getTrailersOfMovie(@Path("id") int id,@Query("api_key") String api_key);

    @GET("movie/{id}/reviews")
    Call<MovieReviewResult> getReviewsOfMovie(@Path("id") int id,@Query("api_key") String api_key);

}