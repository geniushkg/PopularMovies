package com.hardikgoswami.popularmovies.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

import com.tjeannin.provigen.ProviGenOpenHelper;
import com.tjeannin.provigen.ProviGenProvider;

/**
 * Created by geniushkg on 6/17/2016.
 */
public class MovieContentProvider extends ProviGenProvider {

    private static Class[] movieContract  = new Class[]{MovieContract.class};

    @Override
    public SQLiteOpenHelper openHelper(Context context) {
        return new ProviGenOpenHelper(getContext(),"popularmovies",null,1,movieContract);
    }

    @Override
    public Class[] contractClasses() {
        return movieContract;
    }
}
