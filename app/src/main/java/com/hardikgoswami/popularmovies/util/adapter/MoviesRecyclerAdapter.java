package com.hardikgoswami.popularmovies.util.adapter;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.movielist.iMovieListPresenter;
import com.hardikgoswami.popularmovies.util.db.DbBitmapUtility;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<ViewHolder> {
    public static final String TAG = MovieReviewRecyclerAdapter.class.getSimpleName();
    List<MovieEntity> movieEntityList = Collections.emptyList();
    Context context;
    iMovieListPresenter movieListPresenter;
    MovieEntity parcleMovie;

    public MoviesRecyclerAdapter(List<MovieEntity> movieList, Context context, iMovieListPresenter presenter) {
        this.movieEntityList = movieList;
        this.context = context;
        this.movieListPresenter = presenter;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_movielist, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final MovieEntity movieEntity = movieEntityList.get(holder.getAdapterPosition());

        String title = movieEntity.getTitle();
        String rating = String.valueOf(movieEntity.getVote_count());
        Log.d(TAG, "onBindViewHolder: MovieEntity: " + movieEntity.toString());
        holder.title.setText(title);
        holder.rating.setText(rating);
        if (movieEntityList.get(position).getPoster_path() != null) {
            String poster_url = "http://image.tmdb.org/t/p/w185" + movieEntity.getPoster_path();
            Picasso.with(context)
                    .load(poster_url)
                    .into(holder.poster);
        } else {
            Bitmap b = DbBitmapUtility.getImage(movieEntity.getPoster_blob());
            holder.poster.setImageBitmap(b);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieListPresenter.navigateToMovieDetail(movieEntity);
            }
        });

    }


    @Override
    public int getItemCount() {
        return movieEntityList.size();
    }
}
