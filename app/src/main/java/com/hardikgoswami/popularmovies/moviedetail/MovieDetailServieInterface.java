package com.hardikgoswami.popularmovies.moviedetail;

/**
 * Created by geniushkg on 6/15/2016.
 */
public interface MovieDetailServieInterface {
    void fetchReviewsFromSource(int movieId);
    void fetchTrailerFromSource(int movieId);
}
