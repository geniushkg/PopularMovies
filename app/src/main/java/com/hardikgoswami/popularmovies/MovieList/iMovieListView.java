package com.hardikgoswami.popularmovies.MovieList;

/**
 * Created by geniushkg on 6/9/2016.
 */
public interface iMovieListView {
    void showMessage(String message);

    void showLoadingMoviesView();

    void hideLoadingMoviesView();

    void loadMovies();

    void navigateToMovieDetailsView();
}
