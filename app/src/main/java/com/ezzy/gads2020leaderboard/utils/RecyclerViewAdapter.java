package com.ezzy.gads2020leaderboard.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.ezzy.gads2020leaderboard.R;
import com.ezzy.gads2020leaderboard.models.Leaner;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Leaner> learnerList;
    private Context mContext;

    public RecyclerViewAdapter(List<Leaner> learnerList, Context mContext) {
        this.learnerList = learnerList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.gads_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(learnerList.get(position).getName());
        holder.descriptionTextView.setText(learnerList.get(position).getHours() + " Learning Hours,");
        holder.countryTextView.setText(learnerList.get(position).getCountry());
        Glide.with(mContext)
                .load(learnerList.get(position).getBadgeUrl())
                .centerCrop()
                .centerInside()
                .placeholder(R.drawable.learner)
                .into(holder.badgeImageView);
    }

    @Override
    public int getItemCount() {
        return learnerList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView nameTextView, descriptionTextView, countryTextView;
        ImageView badgeImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descTextView);
            badgeImageView = itemView.findViewById(R.id.badge);
            countryTextView = itemView.findViewById(R.id.countryTextView);
        }
    }
}
