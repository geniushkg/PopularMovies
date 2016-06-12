package com.hardikgoswami.popularmovies.util.adapter;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.entity.MovieEntity;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v4.view.ViewParentCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.Collections;
import java.util.List;

/**
 * Created by geniushkg on 6/12/2016.
 */
public class MoviesRecyclerAdapter extends RecyclerView.Adapter<view_holder> {

    List<MovieEntity> movieEntityList = Collections.emptyList();
    Context context;

    public MoviesRecyclerAdapter(List<MovieEntity> movieList, Context context) {
        this.movieEntityList = movieList;
        this.context = context;
    }

    @Override
    public view_holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_movielist,parent,false);
        view_holder view_holder = new view_holder(v);
        return view_holder;
    }

    @Override
    public void onBindViewHolder(view_holder holder, int position) {
        holder.title.setText(movieEntityList.get(position).getOriginal_title());
        holder.rating.setText(movieEntityList.get(position).getVote_count());
        String poster_url ="http://image.tmdb.org/t/p/w185"+ movieEntityList.get(position).getPoster_path();
        Picasso.with(context)
                .load(poster_url)
                .into(holder.poster);
        animate(holder);
    }

    public void animate(RecyclerView.ViewHolder viewHolder) {
        final Animation animAnticipateOvershoot = AnimationUtils.loadAnimation(context, R.anim.bounce_interpolator);
        viewHolder.itemView.setAnimation(animAnticipateOvershoot);
    }

    @Override
    public int getItemCount() {
        int count = movieEntityList.size();
        return count;
    }
}
