package com.hardikgoswami.popularmovies.moviedetail;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragmentImpView extends Fragment implements iMovieDetailView {

    int detailActivityState = 0;
    MovieEntity parceldata;
    public static final String POSTER_URL = "http://image.tmdb.org/t/p/w185";
    String  movie_plot_data = null;
    String htmlText = " %s ";

    public MovieDetailFragmentImpView() {
        // Required empty public constructor
    }

    @BindView(R.id.tv_movie_detail_frag_title)
    TextView tv_movie_title;
    @BindView(R.id.tv_movie_detail_frag_date)
    TextView tv_movie_release_date;
    @BindView(R.id.tv_movie_detail_frag_rating)
    TextView tv_movie_rating;
    @BindView(R.id.iv_movie_detail)
    ImageView iv_movie_poster;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    WebView wv_plot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_fragment_imp_view, container, false);
        ButterKnife.bind(this, rootView);
        ObservableScrollView scrollView = (ObservableScrollView) rootView.findViewById(R.id.sv_movie_detail_fragment) ;
        String htmlText = " %s ";
        String movie_plot = "this is movie plot";
        wv_plot = (WebView) rootView.findViewById(R.id.wv_plot);
        wv_plot.loadData(String.format(htmlText, movie_plot), "text/html", "utf-8");

        floatingActionButton.attachToScrollView(scrollView);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"clicked fab",Toast.LENGTH_SHORT).show();
            }
        });

        Intent intent = getActivity().getIntent();
        if (intent != null) {
            detailActivityState = intent.getIntExtra("detailActivityState", 0);
        }
        if (detailActivityState == 1) {
            MovieDetailActivity movieDetailActivity = (MovieDetailActivity) getActivity();
            parceldata = movieDetailActivity.getMovieEntity();
            tv_movie_title.setText(parceldata.getTitle());
            tv_movie_rating.setText(String.valueOf(parceldata.getVote_average()));
            tv_movie_release_date.setText(parceldata.getRelease_date());
            Picasso.with(getContext())
                    .load(POSTER_URL+ parceldata.getPoster_path())
                    .into(iv_movie_poster);
            movie_plot_data = parceldata.getOverview();
            wv_plot.loadData(String.format(htmlText, movie_plot_data), "text/html", "utf-8");
        }

        return rootView;
    }

    public void displayMovieData(MovieEntity parceldata) {
        tv_movie_title.setText(parceldata.getTitle());
        tv_movie_rating.setText(String.valueOf(parceldata.getVote_average()));
        tv_movie_release_date.setText(parceldata.getRelease_date());
        Picasso.with(getContext())
                .load(POSTER_URL+ parceldata.getPoster_path())
                .into(iv_movie_poster);
        movie_plot_data = parceldata.getOverview();
        wv_plot.loadData(String.format(htmlText, movie_plot_data), "text/html", "utf-8");
    }

    @Override
    public void showReviews() {
        // TODO : this function  is liable to display revies in view
    }

    @Override
    public void showTrailers() {
        // TODO : this function is responsible to show trailer in view
    }
}
