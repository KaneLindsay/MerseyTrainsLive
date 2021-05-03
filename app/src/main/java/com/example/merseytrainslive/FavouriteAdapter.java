package com.example.merseytrainslive;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {

    private ArrayList<Favourite> favouriteList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onDeleteClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public ImageView deleteImage;

        public FavouriteViewHolder(View itemView, OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.line1);
            mTextView2 = itemView.findViewById(R.id.line2);
            deleteImage = itemView.findViewById(R.id.image_delete);

            deleteImage.setOnClickListener(new View.OnClickListener() {
               public void onClick(View v) {
                   int position = getAdapterPosition();
                   if (position != RecyclerView.NO_POSITION) {
                       listener.onDeleteClick(position);
                   }
               }
            });

        }

    }

    public FavouriteAdapter(ArrayList<Favourite> favouriteList) {
        this.favouriteList = favouriteList;
    }

    @NotNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item, parent, false);
        return new FavouriteViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(FavouriteViewHolder holder, int position) {
        Favourite currentFavourite = favouriteList.get(position);
        holder.mImageView.setImageResource(currentFavourite.getImageResource());
        holder.mTextView1.setText(currentFavourite.getText1());
        holder.mTextView2.setText(currentFavourite.getText2());
    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

}
