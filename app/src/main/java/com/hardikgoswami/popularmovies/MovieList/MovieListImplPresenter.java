package com.hardikgoswami.popularmovies.MovieList;

/**
 * Created by geniushkg on 6/9/2016.
 */
public class MovieListImplPresenter implements iMovieListPresenter {
    iMovieListView iMovieListView;
    public MovieListImplPresenter(iMovieListView movieListView){
    this.iMovieListView = movieListView;
    }
    @Override
    public void fetchMovies(String filter) {

    }
}
