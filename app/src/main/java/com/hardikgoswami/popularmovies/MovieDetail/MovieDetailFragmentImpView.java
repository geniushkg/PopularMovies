package com.hardikgoswami.popularmovies.moviedetail;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.adapter.MovieReviewRecyclerAdapter;
import com.hardikgoswami.popularmovies.util.adapter.MovieTrailerRecyclerAdapter;
import com.hardikgoswami.popularmovies.util.db.DbBitmapUtility;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;
import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ObservableScrollView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieDetailFragmentImpView extends Fragment implements iMovieDetailView {
    Context mContext = null;
    int detailActivityState = 0;
    private MovieEntity localMovieEntity;
    public static final String POSTER_URL = "http://image.tmdb.org/t/p/w185";
    String movie_plot_data = null;
    String htmlText = " %s ";
    MovieTrailerRecyclerAdapter movieTrailerRecyclerAdapter;
    MovieReviewRecyclerAdapter movieReviewRecyclerAdapter;
    List<MovieTrailer> movieTrailerList;
    List<MovieReview> movieReviewList;
    iMovieDetailPresenter movieDetailPresenter;

    public MovieDetailFragmentImpView() {
        // Required empty public constructor
    }

    @BindView(R.id.rv_movie_trailers_detail_frag)
    RecyclerView rv_movie_trailers;
    @BindView(R.id.rv_movie_review_detail_frag)
    RecyclerView rv_movie_reviews;
    @BindView(R.id.tv_movie_detail_frag_title)
    TextView tv_movie_title;
    @BindView(R.id.tv_movie_detail_frag_date)
    TextView tv_movie_release_date;
    @BindView(R.id.tv_movie_detail_frag_rating)
    TextView tv_movie_rating;
    @BindView(R.id.iv_movie_detail)
    ImageView iv_movie_poster;
    @BindView(R.id.iv_share_movie_trailer_detail_frag)
    ImageView iv_share_movie_trailer_btn;
    @BindView(R.id.fab)
    FloatingActionButton floatingActionButton;
    WebView wv_plot;
    @BindView(R.id.tv_review_title)
    TextView rv_review_title;
    @BindView(R.id.tv_trailer_title)
    TextView tv_trailer_title;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setRetainInstance(true);
        View rootView = inflater.inflate(R.layout.fragment_movie_detail_fragment_imp_view, container, false);
        ButterKnife.bind(this, rootView);
        mContext = getContext();
        movieDetailPresenter = new MovieDetailImplPresenter(this);
        ObservableScrollView scrollView = (ObservableScrollView) rootView.findViewById(R.id.sv_movie_detail_fragment);
        movieTrailerList = new ArrayList<>();
        movieTrailerRecyclerAdapter = new MovieTrailerRecyclerAdapter(movieTrailerList, mContext, movieDetailPresenter);
        rv_movie_trailers.setAdapter(movieTrailerRecyclerAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_movie_trailers.setLayoutManager(linearLayoutManager);

        movieReviewList = new ArrayList<>();
        movieReviewRecyclerAdapter = new MovieReviewRecyclerAdapter(movieReviewList, mContext);
        rv_movie_reviews.setLayoutManager(new LinearLayoutManager(mContext));


        String htmlText = " %s ";
        String movie_plot = "this is movie plot";
        wv_plot = (WebView) rootView.findViewById(R.id.wv_plot);
        wv_plot.loadData(String.format(htmlText, movie_plot), "text/html", "utf-8");
        floatingActionButton.attachToScrollView(scrollView);

        final MovieEntity localMovieEntity = getParceldata();
        tv_movie_title.setText(localMovieEntity.getTitle());
        tv_movie_rating.setText(String.valueOf(localMovieEntity.getVote_average()));
        tv_movie_release_date.setText(localMovieEntity.getRelease_date());
        if (localMovieEntity.getPoster_path() != null) {
            Picasso.with(getContext())
                    .load(POSTER_URL + localMovieEntity.getPoster_path())
                    .into(iv_movie_poster);
        } else {
            Bitmap b = DbBitmapUtility.getImage(localMovieEntity.getPoster_blob());
            iv_movie_poster.setImageBitmap(b);
        }
        movie_plot_data = localMovieEntity.getOverview();
        wv_plot.loadData(String.format(htmlText, movie_plot_data), "text/html", "utf-8");

        movieDetailPresenter.fetchTrailers(localMovieEntity.getId());
        movieDetailPresenter.fetchReviews(localMovieEntity.getId());
        final Bitmap bitmap = ((BitmapDrawable) iv_movie_poster.getDrawable()).getBitmap();
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDetailPresenter.storeMovieToDb(localMovieEntity, bitmap, mContext);
            }
        });
        return rootView;
    }

    public void displayMovieData(MovieEntity parceldata) {
        tv_movie_title.setText(parceldata.getTitle());
        tv_movie_rating.setText(String.valueOf(parceldata.getVote_average()));
        tv_movie_release_date.setText(parceldata.getRelease_date());
        if (parceldata.getPoster_path() != null) {
            Picasso.with(getContext())
                    .load(POSTER_URL + parceldata.getPoster_path())
                    .into(iv_movie_poster);
        } else {
            Bitmap b = DbBitmapUtility.getImage(parceldata.getPoster_blob());
            iv_movie_poster.setImageBitmap(b);
        }
        movie_plot_data = parceldata.getOverview();
        wv_plot.loadData(String.format(htmlText, movie_plot_data), "text/html", "utf-8");
        if (parceldata.getPoster_path() != null) {
            movieDetailPresenter.fetchTrailers(parceldata.getId());
            movieDetailPresenter.fetchReviews(localMovieEntity.getId());
        }
    }

    @Override
    public void showReviews(List<MovieReview> movieReviewListUpdated) {
        movieReviewList.clear();
        movieReviewList.addAll(movieReviewListUpdated);
        movieReviewRecyclerAdapter.notifyDataSetChanged();
        if (movieReviewList.size() == 0) {
            rv_review_title.setText("0 - Reviews");
            rv_movie_reviews.setVisibility(View.GONE);
        }
        showMessage("list size : " + movieReviewList.size());
        showMessage("adapter size : " + movieReviewRecyclerAdapter.getItemCount());
    }

    @Override
    public void showTrailers(List<MovieTrailer> movieTrailerListUpdated) {
        movieTrailerList.clear();
        movieTrailerList.addAll(movieTrailerListUpdated);
        movieTrailerRecyclerAdapter.notifyDataSetChanged();
        iv_share_movie_trailer_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toShare = "http://www.youtube.com/watch?v="+movieTrailerList.get(0).getKey();
                Intent shareIntent = new Intent(); shareIntent.setAction(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_TEXT, toShare );
                startActivity(Intent.createChooser(shareIntent, "Share via"));
            }
        });
        if (movieTrailerList.size() == 0) {
            rv_movie_trailers.setVisibility(View.GONE);
            tv_trailer_title.setText("0 - Trailers");
            getView().findViewById(R.id.line_lv_trailer).setVisibility(View.GONE);
            getView().findViewById(R.id.line_share).setVisibility(View.GONE);
            getView().findViewById(R.id.iv_share_movie_trailer_detail_frag).setVisibility(View.GONE);
        }
    }

    @Override
    public void playTrailer(String movie_trailer_key) {
        String youtube_url = "http://www.youtube.com/watch?v=" + movie_trailer_key;
        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtube_url)));
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }


    public MovieEntity getParceldata() {
        return localMovieEntity;
    }

    public void setParceldata(MovieEntity parceldata) {
        this.localMovieEntity = parceldata;
    }
}
