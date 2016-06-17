package com.hardikgoswami.popularmovies.movielist;

import android.database.Cursor;
import android.graphics.Bitmap;

/**
 * Created by geniushkg on 6/12/2016.
 */

public interface IListenerDb<T> {

    void onSucessData(Cursor cursor);

    void onFailure(String errorMessage);
}