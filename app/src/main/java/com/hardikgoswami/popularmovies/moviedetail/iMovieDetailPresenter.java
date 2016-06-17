package com.hardikgoswami.popularmovies.moviedetail;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

/**
 * Created by geniushkg on 6/15/2016.
 */
public interface iMovieDetailPresenter {
    void fetchReviews(int movieId);
    void fetchTrailers(int movieId);
    void playTrailer(String key);
    void storeMovieToDb(MovieEntity favouriteMovie);
}
