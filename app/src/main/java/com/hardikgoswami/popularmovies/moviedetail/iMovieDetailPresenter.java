package com.hardikgoswami.popularmovies.moviedetail;

/**
 * Created by geniushkg on 6/15/2016.
 */
public interface iMovieDetailPresenter {
    void fetchReviews(int movieId);
    void fetchTrailers(int movieId);
}
