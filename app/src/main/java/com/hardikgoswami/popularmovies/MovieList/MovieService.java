package com.hardikgoswami.popularmovies.movielist;

import com.hardikgoswami.popularmovies.PopularMovieApplication;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.hardikgoswami.popularmovies.util.entity.RestResult;
import java.util.List;
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

            callBack.onFailure("empty favourite , db implementation pending ");// will be called on failure of service

        } else if (filter.equalsIgnoreCase("popular")) {

            callBack.onFailure("popular ");// will be called on failure of service
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
                    callBack.onFailure("onFailure message : " + t.getMessage());
                }
            });

        } else {

            callBack.onFailure("top rated");// will be called on failure of service
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
                    callBack.onFailure("onFailure message : " + t.getMessage());
                }
            });
        }
    }
}
