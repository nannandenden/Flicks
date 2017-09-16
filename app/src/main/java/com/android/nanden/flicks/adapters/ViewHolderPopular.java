package com.android.nanden.flicks.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.flicks.R;

public class ViewHolderPopular extends RecyclerView.ViewHolder {

    private TextView tvTitle;
    private TextView tvOverView;
    private ImageView ivFullBackDrop;

    public ViewHolderPopular(View itemView) {
        super(itemView);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvOverView = itemView.findViewById(R.id.tvOverview);
        ivFullBackDrop = itemView.findViewById(R.id.ivFullBackDrop);
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public TextView getTvOverView() {
        return tvOverView;
    }

    public ImageView getIvFullBackDrop() {
        return ivFullBackDrop;
    }
}
