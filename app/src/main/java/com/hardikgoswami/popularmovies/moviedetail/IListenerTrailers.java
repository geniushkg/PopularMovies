package com.hardikgoswami.popularmovies.moviedetail;

/**
 * Created by geniushkg on 6/12/2016.
 */

public interface IListenerTrailers<T> {

    void onSuccessTrailers(T t);

    void onFailure(String errorMessage);

}