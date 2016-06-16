package com.hardikgoswami.popularmovies.moviedetail;

import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

/**
 * Created by geniushkg on 6/15/2016.
 */
public class MovieDetailImplPresenter implements iMovieDetailPresenter,IListener  {
    iMovieDetailView movieDetailView;
    MovieDetailServieInterface movieDetailServieInterface;
    MovieDetailImplPresenter( iMovieDetailView movieDetailView ){
        this.movieDetailView = movieDetailView;
        movieDetailServieInterface = new MovieDetailService();
    }

    @Override
    public void fetchReviews(int movieId) {
        movieDetailServieInterface.fetchReviewsFromSource(movieId,this);
    }

    @Override
    public void fetchTrailers(int movieId) {
        movieDetailServieInterface.fetchTrailerFromSource(movieId,this);

    }

    @Override
    public void playTrailer(String key) {
        movieDetailView.playTrailer(key);
    }


    @Override
    public void onSuccessReviews(Object o) {
        movieDetailView.showReviews((List<MovieReview>) o);
    }

    @Override
    public void onSuccessTrailers(Object o) {
        movieDetailView.showTrailers((List<MovieTrailer>) o);
    }

    @Override
    public void onFailure(String errorMessage) {

    }
}
