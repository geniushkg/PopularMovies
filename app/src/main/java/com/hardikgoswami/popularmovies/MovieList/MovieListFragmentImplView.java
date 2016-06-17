package com.hardikgoswami.popularmovies.movielist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.PopularMovieApplication;
import com.hardikgoswami.popularmovies.util.adapter.MoviesRecyclerAdapter;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.hardikgoswami.popularmovies.R;


public class MovieListFragmentImplView extends Fragment implements iMovieListView, AdapterView.OnItemSelectedListener {

    public MovieListFragmentImplView() {
    }

    public sortOrder SORT_ORDER = sortOrder.POPULAR;
    iMovieListPresenter movieListPresenterInterface;
    @BindView(R.id.moviesloadingIndicatorView)
    View loadAnimView;
    @BindView(R.id.recycleview_movieslist_fragment)
    RecyclerView recyclerView;
    MoviesRecyclerAdapter moviesRecyclerAdapter;
    Context mContext;
    List<MovieEntity> movieEntitiesEmpty;
    int numOfColoums = 2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list_fragment_impl_view, container, false);
        ButterKnife.bind(this, rootView);
        movieListPresenterInterface = new MovieListImplPresenter(this);
        mContext = getContext();
        movieEntitiesEmpty = new ArrayList<>();
        moviesRecyclerAdapter = new MoviesRecyclerAdapter(movieEntitiesEmpty, mContext, movieListPresenterInterface);
        recyclerView.setAdapter(moviesRecyclerAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), numOfColumnsForOrientation()));
        if (PopularMovieApplication.isNetworkStatusAvialable(mContext)) {
            updateAdapterData();
        } else {
            showMessage("Internet Not Available , Please turn on Mobile Data");
        }
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
        boolean dualPane = false;
        MovieEntity parcelMovie = (MovieEntity) movieEntitiesNew.get(0);
        MovieListActivity movieListActivity = (MovieListActivity) getActivity();
        if (movieListActivity != null) {
            dualPane = movieListActivity.isDualPane();
        }
        if (dualPane) {
            if (movieListActivity != null) {
                movieListActivity.updateUi(parcelMovie);
            }
        }
    }

    @Override
    public void navigateToMovieDetailsView(MovieEntity parcelMovie) {
        // we have movie details , just open new activity or adjust frags as per master details flow
        MovieListActivity movieListActivity = (MovieListActivity) getActivity();
        if (movieListActivity != null) {
            movieListActivity.updateUi(parcelMovie);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.sort_order, R.layout.spinner_layout_custom);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_custom);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("popular")) {
            SORT_ORDER = sortOrder.POPULAR;
        } else if (parent.getItemAtPosition(position).toString().equalsIgnoreCase("top rated")) {
            SORT_ORDER = sortOrder.TOP_RATED;
        } else {
            SORT_ORDER = sortOrder.OFFLINE;
        }
        updateAdapterData();
    }

    public void updateAdapterData() {
        switch (SORT_ORDER) {
            case TOP_RATED: {
                movieListPresenterInterface.fetchMovies("top_rated");
                break;
            }
            case POPULAR: {
                movieListPresenterInterface.fetchMovies("popular");
                break;
            }
            case OFFLINE: {
                movieListPresenterInterface.fetchMoviesFavourite(getContext());
                break;
            }
            default: {
                showMessage("Sorting Error");
                break;
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getContext(), "Nothing selected", Toast.LENGTH_SHORT).show();
    }

    public enum sortOrder {
        TOP_RATED, POPULAR, OFFLINE
    }

    public int numOfColumnsForOrientation() {
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        int width = display.getWidth();
        int height = display.getHeight();

        if (width < height) {
            // portrait mode
            numOfColoums = 2;
            if (width > 600) {
                numOfColoums = 3;
            }
        } else {
            // landscape mode
            numOfColoums = 4;
            if (width > 600) {
                numOfColoums = 5;
            }
        }
        return numOfColoums;
    }
}
