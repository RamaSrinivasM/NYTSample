package com.nytsample.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.nytsample.R;
import com.nytsample.activites.NewsItemActivity;
import com.nytsample.model.MostPopularResponse;
import com.nytsample.model.Results;
import com.nytsample.utility.Constants;
import com.nytsample.utility.Display;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private static List<Results> itemList;
    private static Context mContext;

    // Constructor of the class
    public NewsListAdapter(Context context, List<Results> itemList) {
        this.mContext = context;
        this.itemList = itemList;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        final String name = itemList.get(listPosition).getTitle();
        holder.mTitleTV.setText(name);
        holder.mDateTV.setText(itemList.get(listPosition).getPublishedDate());

        holder.mByNameTV.setText(itemList.get(listPosition).getByline());


        Glide.with(mContext).load(itemList.get(listPosition).getMedia().get(0).getMediaMetadata().get(0).getUrl()).asBitmap().centerCrop().into(new BitmapImageViewTarget(holder.mLogoIV) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(mContext.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                holder.mLogoIV.setImageDrawable(circularBitmapDrawable);
            }
        });
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView mTitleTV;
        public TextView mDateTV;
        public ImageView mLogoIV;
        public TextView mByNameTV;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTV = (TextView) itemView.findViewById(R.id.title_tv);
            mLogoIV = (ImageView)itemView.findViewById(R.id.logo_iv);
            mDateTV = (TextView)itemView.findViewById(R.id.date_tv);
            mByNameTV = (TextView)itemView.findViewById(R.id.by_name_tv);
        }
        @Override
        public void onClick(View view) {
            Display.DisplayLogD("onclick", "onClick " + getLayoutPosition() + " " + itemList.get(getLayoutPosition()).getUrl());
            Intent intent = new Intent(view.getContext(), NewsItemActivity.class);
            intent.putExtra(Constants.NEWS_URL,itemList.get(getLayoutPosition()).getUrl());
            mContext.startActivity(intent);
        }
    }
}