package com.hardikgoswami.popularmovies.moviedetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments().getInt("someInt", 0);
        if(getArguments() != null){
            detailActivityState = getArguments().getInt("detailActivity");
        }
    }
    @BindView(R.id.tv_movie_detail_frag_title)
    TextView movie_title;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_fragment_imp_view, container, false);
        ButterKnife.bind(this,rootView);
        if(detailActivityState == 1){
            MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
            parceldata = movieDetailActivity.getMovieEntity();
            movie_title.setText(parceldata.getTitle());
        }
        return rootView;
    }
    public void displayMovieData(MovieEntity parceldata){
 // display ui and display data as per parcel movieEntity recieved
        movie_title.setText(parceldata.getTitle());
    }

}
