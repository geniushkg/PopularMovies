package com.hardikgoswami.popularmovies.util.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hardikgoswami.popularmovies.R;
import com.hardikgoswami.popularmovies.util.entity.MovieReview;
import java.util.List;

public class MovieReviewRecyclerAdapter extends RecyclerView.Adapter<MovieReviewRecyclerAdapter.ViewHolder> {

    List<MovieReview> movieReviewList;
    Context mContext = null;

    public MovieReviewRecyclerAdapter(List<MovieReview> movieReviewListIn, Context context) {
        this.movieReviewList = movieReviewListIn;
        this.mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout_movie_review, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MovieReview movieReview = movieReviewList.get(position);
        holder.tv_author_review.setText(movieReview.getAuthor());
        holder.tv_content_review.setText(movieReview.getContent());
    }

    @Override
    public int getItemCount() {
        return movieReviewList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_author_review, tv_content_review;

        public ViewHolder(View itemView) {
            super(itemView);

            tv_author_review = (TextView) itemView.findViewById(R.id.tv_movie_review_author);
            tv_content_review = (TextView) itemView.findViewById(R.id.tv_movie_review_content);
        }
    }
}
