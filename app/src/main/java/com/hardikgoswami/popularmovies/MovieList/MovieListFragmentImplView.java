package com.hardikgoswami.popularmovies.movielist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.hardikgoswami.popularmovies.util.adapter.MoviesRecyclerAdapter;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import java.util.Collections;
import java.util.List;
import com.hardikgoswami.popularmovies.R;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListFragmentImplView extends Fragment implements iMovieListView {

    public MovieListFragmentImplView() {
    }

    @BindView(R.id.moviesloadingIndicatorView)
    View loadAnimView;
    @BindView(R.id.recycleview_movieslist_fragment)
    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;
    Context mContext;
    List<MovieEntity> movieEntitiesEmpty;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list_fragment_impl_view, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getContext();
        movieEntitiesEmpty  = Collections.emptyList();
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(movieEntitiesEmpty,mContext);
        recyclerView.setAdapter(moviesRecyclerAdapter);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
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
    public void loadMovies(List<MovieEntity> movieEntitiesNew) {
        // load movies to grid
        movieEntitiesEmpty.clear();
        movieEntitiesEmpty.addAll(movieEntitiesNew);
        moviesRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void navigateToMovieDetailsView() {
        // onclick grid , jump to details of movie clicked
    }
}
