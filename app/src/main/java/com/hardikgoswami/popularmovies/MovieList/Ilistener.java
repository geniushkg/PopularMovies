package com.hardikgoswami.popularmovies.movielist;

/**
 * Created by geniushkg on 6/12/2016.
 */

public interface IListener<T> {

    void onSuccess(T t);

    void onFailure(String errorMessage);
}