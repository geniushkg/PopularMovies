package com.hardikgoswami.popularmovies.moviedetail;

import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;

import java.util.List;

/**
 * Created by geniushkg on 6/15/2016.
 */
public interface MovieDetailServieInterface {
    void fetchReviewsFromSource(int movieId , IListener<List<MovieReview>> callback);
    void fetchTrailerFromSource(int movieId , IListener<List<MovieTrailer>> callback);
}
