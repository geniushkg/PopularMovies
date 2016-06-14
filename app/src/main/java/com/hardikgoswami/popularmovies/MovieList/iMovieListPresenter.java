package com.hardikgoswami.popularmovies.movielist;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

/**
 * Created by geniushkg on 6/9/2016.
 */
public interface iMovieListPresenter {
 void fetchMovies(String filter);
 void navigateToMovieDetail(MovieEntity parcelMovie);
}
