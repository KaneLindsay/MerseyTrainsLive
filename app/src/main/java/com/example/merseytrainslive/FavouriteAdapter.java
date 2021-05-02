package com.example.merseytrainslive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private ArrayList<Favourite> favouriteList;

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public FavouriteViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.line1);
            mTextView2 = itemView.findViewById(R.id.line2);

        }
    }

    public FavouriteAdapter(ArrayList<Favourite> favouriteList) {
        this.favouriteList = favouriteList;
    }

    @NotNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
        return new FavouriteViewHolder(v);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        Favourite currentFavourite = favouriteList.get(position);
        holder.mImageView.setImageResource(currentFavourite.getImageResource());
        holder.mTextView1.setText(currentFavourite.getText1());
        holder.mTextView2.setText("To " + currentFavourite.getText2());
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }
}
