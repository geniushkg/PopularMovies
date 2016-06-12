package com.hardikgoswami.popularmovies.MovieList;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListFragmentImplView extends Fragment implements iMovieListView {

    public MovieListFragmentImplView() {
    }

    @BindView(R.id.moviesloadingIndicatorView)
    View loadAnimView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list_fragment_impl_view, container, false);
        ButterKnife.bind(this, rootView);


        return rootView;
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingMoviesView() {
        // show loader
         loadAnimView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingMoviesView() {
        // hide loader
         loadAnimView.setVisibility(View.GONE);
    }

    @Override
    public void loadMovies() {
        // load movies to grid
    }

    @Override
    public void navigateToMovieDetailsView() {
        // onclick grid , jump to details of movie clicked
    }
}
