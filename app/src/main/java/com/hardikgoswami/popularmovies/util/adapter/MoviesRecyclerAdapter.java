package com.hardikgoswami.popularmovies.util.adapter;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.movielist.iMovieListPresenter;
import com.hardikgoswami.popularmovies.util.db.DbBitmapUtility;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collections;
import java.util.List;

/**
 * Created by geniushkg on 6/12/2016.
 */
public class MoviesRecyclerAdapter extends RecyclerView.Adapter<view_holder> {

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
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_movielist, parent, false);
        view_holder view_holder = new view_holder(v);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(view_holder holder, final int position) {
        holder.title.setText(movieEntityList.get(position).getTitle());
        holder.rating.setText(String.valueOf(movieEntityList.get(position).getVote_count()));
        if (movieEntityList.get(position).getPoster_path() != null) {
            String poster_url = "http://image.tmdb.org/t/p/w185" + movieEntityList.get(position).getPoster_path();
            Picasso.with(context)
                    .load(poster_url)
                    .into(holder.poster);
        } else {
            Bitmap b = DbBitmapUtility.getImage(movieEntityList.get(position).getPoster_blob());
            holder.poster.setImageBitmap(b);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parcleMovie = (MovieEntity) movieEntityList.get(position);
                movieListPresenter.navigateToMovieDetail(parcleMovie);
            }
        });

    }


    @Override
    public int getItemCount() {
        int count = movieEntityList.size();
        return count;
    }
}
