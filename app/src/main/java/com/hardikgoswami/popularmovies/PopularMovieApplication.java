package com.hardikgoswami.popularmovies;

import android.app.Application;

import com.hardikgoswami.popularmovies.util.net.TheMovieDbService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by geniushkg on 5/29/2016.
 */
public class PopularMovieApplication extends Application {
    public static final String BASE_URL ="http://api.themoviedb.org/3/";
    public static final String TMDB_API_KEY = BuildConfig.TMDB_API;
    private static TheMovieDbService sService;

    @Override
    public void onCreate() {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        sService = retrofit.create(TheMovieDbService.class);
    }
    public static TheMovieDbService getsService(){
        if (sService == null){
            throw new NullPointerException("Service Null");
        }
        return sService;
    }
}
