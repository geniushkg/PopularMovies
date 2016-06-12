package com.hardikgoswami.popularmovies.movielist;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import java.util.List;

/**
 * Created by geniushkg on 6/9/2016.
 */
public class MovieListImplPresenter implements iMovieListPresenter, IListener<List<MovieEntity>> {

    iMovieListView iMovieListView;
    MovieServiceInterface movieServiceInterface;

    public MovieListImplPresenter(iMovieListView movieListView) {
        iMovieListView = movieListView;
        movieServiceInterface = new MovieService();
    }

    @Override
    public void fetchMovies(String filter) {
        iMovieListView.showLoadingMoviesView();
        movieServiceInterface.fetchMoviesFromSource(filter, this);
    }

    @Override
    public void onSuccess(List<MovieEntity> movieEntities) {
        iMovieListView.hideLoadingMoviesView();
        iMovieListView.loadMovies(movieEntities);

    }

    @Override
    public void onFailure(String errorMessage) {
        iMovieListView.hideLoadingMoviesView();
        iMovieListView.showMessage(errorMessage);
    }
}
