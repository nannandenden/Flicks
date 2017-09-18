package com.android.nanden.flicks.adapters;


import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.nanden.flicks.R;
import com.android.nanden.flicks.models.Movie;
import com.android.nanden.flicks.models.Movie.StarCount;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindDrawable;
import butterknife.ButterKnife;

public class MovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<Movie> movies;
    private Context context;
    @BindDrawable(R.drawable.placeholder)
    Drawable placeHolder;
    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @Override
    public int getItemCount() {
        return this.movies.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (movies.get(position).starCount == StarCount.POPULAR) {
            return StarCount.POPULAR.ordinal();
        } else if (movies.get(position).starCount == StarCount.SOSO) {
            return StarCount.SOSO.ordinal();
        }
        return -1;
    }
    // Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an item.
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ButterKnife.bind(this, parent);
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == StarCount.POPULAR.ordinal()) {
            View viewPopular = inflater.inflate(R.layout.item_movie_popular, parent, false);
            viewHolder = new ViewHolderPopular(viewPopular);
        } else if (viewType == StarCount.SOSO.ordinal()) {
            View viewSoso = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolderSoso(viewSoso);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == StarCount.POPULAR.ordinal()) {
            ViewHolderPopular vhPopular = (ViewHolderPopular) holder;
            configViewHolderPopular(vhPopular, position);
        } else if (holder.getItemViewType() == StarCount.SOSO.ordinal()) {
            ViewHolderSoso vhSoso = (ViewHolderSoso) holder;
            configViewHolderSoso(vhSoso, position);
        }

    }

    private void configViewHolderPopular(ViewHolderPopular vhPopular, int position) {
        vhPopular.getIvFullBackDrop().setImageResource(0);
        Picasso.with(context).load(movies.get(position).getBackDropPath())
                .placeholder(placeHolder)
                .into(vhPopular.getIvFullBackDrop());
        int orientation = context.getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            vhPopular.getTvTitle().setText(movies.get(position).getOriginalTitle());
            vhPopular.getTvOverView().setText(movies.get(position).getOverview());
        }
    }

    private void configViewHolderSoso(ViewHolderSoso vhSoso, int position) {
        vhSoso.getIvPoster().setImageResource(0);
        Picasso.with(context).load(movies.get(position).getPosterPath())
                .placeholder(placeHolder)
                .into(vhSoso.getIvPoster());
        vhSoso.getTvTitle().setText(movies.get(position).getOriginalTitle());
        vhSoso.getTvOverView().setText(movies.get(position).getOverview());
    }
}
