package com.android.nanden.flicks.adapters;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.nanden.flicks.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewHolderPopular extends RecyclerView.ViewHolder {

    @Nullable
    @BindView(R.id.tvTitle) TextView tvTitle;
    @Nullable
    @BindView(R.id.tvOverview) TextView tvOverView;
    @Nullable
    @BindView(R.id.ivFullBackDrop) ImageView ivFullBackDrop;

    public ViewHolderPopular(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
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
