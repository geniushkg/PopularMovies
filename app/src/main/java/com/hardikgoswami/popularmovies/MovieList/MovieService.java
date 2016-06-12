package com.hardikgoswami.popularmovies.movielist;

import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import java.util.Collections;
import java.util.List;

/**
 * Created by geniushkg on 6/12/2016.
 */
public class MovieService implements MovieServiceInterface {

    public MovieService(){

    }

    @Override
    public void fetchMoviesFromSource(String filter, IListener<List<MovieEntity>> callBack) {
        // dummy data sevicce
        // dummy movie list
        List<MovieEntity> movieEntitiesDummy = Collections.emptyList();
        callBack.onSuccess(movieEntitiesDummy);//will be called on succes of service
        callBack.onFailure("failed ");// will be called on failure of service
    }
}
