package com.android.nanden.flicks.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.flicks.R;
import com.android.nanden.flicks.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        // check to see if existing view being reused
        if (convertView == null) { // if it's null means there is no view that can be reused so
            // we want to inflate the custom view
            LayoutInflater inflater = LayoutInflater.from(getContext()); // context is passed by
            // the MovieArrayAdapter constructor
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            // use R.layout.item_movie to inflate the data
            // parent values for root of the returned hierarchy
            // false Whether the inflated hierarchy should be attached to the root parameter
        }

        // populate the items
        ImageView ivPoster = convertView.findViewById(R.id.ivPoster);
        ImageView ivBackDrop = convertView.findViewById(R.id.ivBackDrop);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = convertView.findViewById(R.id.tvOverview);

        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            // clear out image from convertView. need to clear inside the orientation condition
            // since different orientation will not see this imageView
            ivPoster.setImageResource(0);
            Picasso.with(getContext()).load(movie.getPosterPath()).into(ivPoster);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            ivBackDrop.setImageResource(0);
            Picasso.with(getContext()).load(movie.getBackDropPath()).into(ivBackDrop);
        }

        return convertView;
    }

}
