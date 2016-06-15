package com.hardikgoswami.popularmovies.moviedetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragmentImpView extends Fragment {

    int detailActivityState = 0;
    MovieEntity parceldata;

    public MovieDetailFragmentImpView() {
        // Required empty public constructor
    }
    public TextView movie_title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_fragment_imp_view, container, false);
        movie_title = (TextView) rootView.findViewById(R.id.tv_movie_detail_frag_title);
        Intent intent = getActivity().getIntent();
        if (intent != null) {
            detailActivityState = intent.getIntExtra("detailActivityState", 0);
        }
        if (detailActivityState == 1) {
            MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
            parceldata = movieDetailActivity.getMovieEntity();
            movie_title.setText(parceldata.getTitle());
        }
        Toast.makeText(getContext(), "this is from oncreate view", Toast.LENGTH_SHORT).show();
        return rootView;
    }

    public void displayMovieData(MovieEntity parceldata) {
        // display ui and display data as per parcel movieEntity recieved
        movie_title.setText(parceldata.getTitle());
        Toast.makeText(getContext(), "this is from helper method", Toast.LENGTH_SHORT).show();
    }
}
