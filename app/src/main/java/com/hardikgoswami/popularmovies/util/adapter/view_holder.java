package com.hardikgoswami.popularmovies.util.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.hardikgoswami.popularmovies.R;

import butterknife.BindView;

/**
 * Created by geniushkg on 6/12/2016.
 */
public class view_holder extends RecyclerView.ViewHolder {
    CardView cv;
    TextView rating,title;
    ImageView poster;
    public view_holder(View itemView) {
        super(itemView);
        cv = (CardView) itemView.findViewById(R.id.cv_row_movielist_element);
        poster = (ImageView) itemView.findViewById(R.id.image_row_movielist);
        rating = (TextView) itemView.findViewById(R.id.text_row_movielist_rating);
        title = (TextView) itemView.findViewById(R.id.text_row_movielist_title);

    }
}
