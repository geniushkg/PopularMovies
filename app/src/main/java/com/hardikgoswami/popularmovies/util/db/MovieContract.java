package com.hardikgoswami.popularmovies.util.db;

import android.net.Uri;

import com.tjeannin.provigen.ProviGenBaseContract;
import com.tjeannin.provigen.annotation.Column;
import com.tjeannin.provigen.annotation.ContentUri;

/**
 * Created by geniushkg on 6/17/2016.
 */
public interface MovieContract extends ProviGenBaseContract {
    @Column(Column.Type.INTEGER)
    public static final String MOVIE_ID = "id";

    @Column(Column.Type.INTEGER)
    public static final String VOTE_AVG_COLOUMN = "vote_avg";


    @Column(Column.Type.TEXT)
    public static final String PLOT_MOVIE = "plot_movie";


    @Column(Column.Type.TEXT)
    public static final String TITLE_MOVIE = "title_movie";


    @Column(Column.Type.TEXT)
    public static final String RELEASE_DATE = "release_date_movie";


    @Column(Column.Type.BLOB)
    public static final String POSTER_MOVIE = "poster_movie";

    @ContentUri
    public static final Uri CONTENT_URI = Uri.parse("content://com.hardikgoswami/favourite_movie");
}
