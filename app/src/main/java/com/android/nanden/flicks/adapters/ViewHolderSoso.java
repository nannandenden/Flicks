package com.android.nanden.flicks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.flicks.R;

public class ViewHolderSoso extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvOverView;
    private ImageView ivPoster;

    public ViewHolderSoso(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverView = itemView.findViewById(R.id.tvOverview);
        ivPoster = itemView.findViewById(R.id.ivPoster);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvOverView() {
        return tvOverView;
    }

    public ImageView getIvPoster() {
        return ivPoster;
    }
}
