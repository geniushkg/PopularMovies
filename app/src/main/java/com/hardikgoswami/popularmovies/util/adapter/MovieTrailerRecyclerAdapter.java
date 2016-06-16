package com.hardikgoswami.popularmovies.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.moviedetail.iMovieDetailPresenter;
import com.hardikgoswami.popularmovies.util.entity.MovieTrailer;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by geniushkg on 6/16/2016.
 */
public class MovieTrailerRecyclerAdapter extends RecyclerView.Adapter<MovieTrailerRecyclerAdapter.ViewHolder> {

    List<MovieTrailer> mMovieTrailer;
    Context mContext = null;
    iMovieDetailPresenter movieDetailPresenter;

    public MovieTrailerRecyclerAdapter(List<MovieTrailer> movieTrailerList,iMovieDetailPresenter presenter){
        this.mMovieTrailer = movieTrailerList;
        this.movieDetailPresenter = presenter;
    }
    @Override
    public MovieTrailerRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View movieTrailerView = inflater.inflate(R.layout.row_layout_movie_trailer,parent,false);
        MovieTrailerRecyclerAdapter.ViewHolder viewHolder = new MovieTrailerRecyclerAdapter.ViewHolder(movieTrailerView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieTrailer movieTrailer = mMovieTrailer.get(position);
        final String movie_trailer_key = movieTrailer.getKey();
        String thumbnail_image_url = "http://img.youtube.com/vi/"+movie_trailer_key+"/mqdefault.jpg";
        Picasso.with(mContext)
                .load(thumbnail_image_url)
                .placeholder(R.drawable.progress_animation)
                .into(holder.trailer_thumbnail);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movieDetailPresenter.playTrailer(movie_trailer_key);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public ImageView trailer_thumbnail;

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            trailer_thumbnail = (ImageView) itemView.findViewById(R.id.iv_row_rv_movie_trailer_detail_frag);

        }
    }
}
