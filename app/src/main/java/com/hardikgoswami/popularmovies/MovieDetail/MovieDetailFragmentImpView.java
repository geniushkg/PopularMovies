package com.hardikgoswami.popularmovies.moviedetail;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hardikgoswami.popularmovies.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragmentImpView extends Fragment {


    public MovieDetailFragmentImpView() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_fragment_imp_view, container, false);

        return rootView;
    }

}
