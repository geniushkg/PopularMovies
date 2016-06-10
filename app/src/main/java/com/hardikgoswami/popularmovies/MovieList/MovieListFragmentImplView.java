package com.hardikgoswami.popularmovies.MovieList;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hardikgoswami.popularmovies.R;


public class MovieListFragmentImplView extends Fragment {


    public MovieListFragmentImplView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list_fragment_impl_view,container,false);



        return rootView;
    }


}
