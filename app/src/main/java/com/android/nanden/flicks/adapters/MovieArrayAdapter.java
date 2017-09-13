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

import static com.android.nanden.flicks.R.id.ivBackDrop;
import static com.android.nanden.flicks.R.id.ivPoster;

public class MovieArrayAdapter extends ArrayAdapter<Movie>{

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        ImageView ivPoster;
        ImageView ivBackDrop;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        ViewHolder viewHolder;  // view lookup cache stored in tag

        // check to see if existing view being reused
        if (convertView == null) { // if it's null means there is no view that can be reused
            viewHolder = new ViewHolder();
            // thus, we want to inflate the custom view
            LayoutInflater inflater = LayoutInflater.from(getContext()); // context is passed by
            // the MovieArrayAdapter constructor
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            // use R.layout.item_movie to inflate the data
            // parent values for root of the returned hierarchy
            // false Whether the inflated hierarchy should be attached to the root parameter
            viewHolder.tvTitle = convertView.findViewById(R.id.tvTitle);
            viewHolder.tvOverview = convertView.findViewById(R.id.tvOverview);
            viewHolder.ivPoster = convertView.findViewById(ivPoster);
            viewHolder.ivBackDrop = convertView.findViewById(ivBackDrop);
            // cache the viewHolder object inside the fresh view
            // Sets the tag associated with this view. Tags can also be used to store data within
            // a view without resorting to another data structure.
            convertView.setTag(viewHolder);
        } else {
            // view is begin recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // populate the items
        // populate the data from the data object via the viewHolder object into the template view
        viewHolder.tvTitle.setText(movie.getOriginalTitle());
        viewHolder.tvOverview.setText(movie.getOverview());

        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT) {
            // clear out image from convertView. need to clear inside the orientation condition
            // since different orientation will not see this imageView
            viewHolder.ivPoster.setImageResource(0);
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.ivPoster);
        } else if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            viewHolder.ivBackDrop.setImageResource(0);
            Picasso.with(getContext()).load(movie.getBackDropPath()).into(viewHolder.ivBackDrop);
        }

        return convertView;
    }

}
