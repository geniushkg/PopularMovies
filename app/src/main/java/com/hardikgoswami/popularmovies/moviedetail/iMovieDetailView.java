package com.hardikgoswami.popularmovies.moviedetail;

import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;

import java.util.List;

/**
 * Created by geniushkg on 6/15/2016.
 */
public interface iMovieDetailView {
    void showReviews(List<MovieReview> movieReviews);
    void showTrailers(List<MovieTrailer> movieTrailers);
    void playTrailer(String movie_trailer_key);
    void showMessage(String message);
}
