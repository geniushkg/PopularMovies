package com.hardikgoswami.popularmovies.movielist;

import android.content.Context;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

/**
 * Created by geniushkg on 6/9/2016.
 */
public interface iMovieListPresenter {
 void fetchMovies(String filter);
 void fetchMoviesFavourite(Context mContext);
 void navigateToMovieDetail(MovieEntity parcelMovie);
}
