package com.hardikgoswami.popularmovies.moviedetail;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;

import java.util.List;

/**
 * Created by geniushkg on 6/15/2016.
 */
public interface MovieDetailServieInterface {
    void fetchReviewsFromSource(int movieId , IListenerReviews<List<MovieReview>> callback);
    void fetchTrailerFromSource(int movieId , IListenerTrailers<List<MovieTrailer>> callback);
    void storeMovieToDb(MovieEntity favouriteMovie);
}
