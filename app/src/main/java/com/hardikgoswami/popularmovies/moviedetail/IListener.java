package com.hardikgoswami.popularmovies.moviedetail;

/**
 * Created by geniushkg on 6/12/2016.
 */

public interface IListener<T> {

    void onSuccessReviews(T t);

    void onSuccessTrailers(T t);

    void onFailure(String errorMessage);

}