package com.hardikgoswami.popularmovies.util.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardikgoswami.popularmovies.R;

import butterknife.BindView;

public class ViewHolder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView rating,title;
    ImageView poster;
    public ViewHolder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv_row_movielist_element);
        poster = (ImageView) itemView.findViewById(R.id.image_row_movielist);
        rating = (TextView) itemView.findViewById(R.id.text_row_movielist_rating);
        title = (TextView) itemView.findViewById(R.id.text_row_movielist_title);
    }
}
