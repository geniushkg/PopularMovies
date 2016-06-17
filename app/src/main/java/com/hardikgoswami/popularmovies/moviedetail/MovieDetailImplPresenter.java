package com.hardikgoswami.popularmovies.moviedetail;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;

import com.hardikgoswami.popularmovies.movielist.IListener;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;

import java.util.List;

/**
 * Created by geniushkg on 6/15/2016.
 */
public class MovieDetailImplPresenter implements iMovieDetailPresenter,IListenerReviews<List<MovieReview>>,IListenerTrailers<List<MovieTrailer>>,IListener {
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
    public void storeMovieToDb(MovieEntity favouriteMovie, Bitmap poster ,Context mContext) {
        movieDetailServieInterface.storeMovieToDb(favouriteMovie,poster, mContext,this);
    }


    @Override
    public void onSuccessReviews(List<MovieReview> movieReviews) {
        movieDetailView.showReviews(movieReviews);
    }

    @Override
    public void onSuccessTrailers(List<MovieTrailer> movieTrailerList) {
        movieDetailView.showTrailers(movieTrailerList);
    }


    @Override
    public void onSuccess(Object o) {

    }

    @Override
    public void onFailure(String errorMessage) {
        movieDetailView.showMessage(errorMessage);
    }
}
