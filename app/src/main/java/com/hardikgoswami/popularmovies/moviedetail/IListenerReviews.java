package com.hardikgoswami.popularmovies.moviedetail;

/**
 * Created by geniushkg on 6/12/2016.
 */

public interface IListenerReviews<T> {

    void onSuccessReviews(T t);

    void onFailure(String errorMessage);

}