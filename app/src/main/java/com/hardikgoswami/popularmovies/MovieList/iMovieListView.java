package com.hardikgoswami.popularmovies.movielist;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import java.util.List;

/**
 * Created by geniushkg on 6/9/2016.
 */
public interface iMovieListView {
    void showMessage(String message);

    void showLoadingMoviesView();

    void hideLoadingMoviesView();

    void loadMovies(List<MovieEntity> movieEntities);

    void navigateToMovieDetailsView(MovieEntity parcelMovie);
}
