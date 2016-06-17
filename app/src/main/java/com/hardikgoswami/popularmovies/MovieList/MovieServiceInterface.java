package com.hardikgoswami.popularmovies.movielist;

import android.content.Context;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import java.util.List;

/**
 * Created by geniushkg on 6/12/2016.
 */
public interface  MovieServiceInterface {
    void fetchMoviesFromSource(String filter, IListener<List<MovieEntity>> callBack);
    void fetchFavouriteMovies(IListener<List<MovieEntity>> callBack, Context context);
}
