package com.ardnn.movieapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ardnn.movieapp.R;
import com.ardnn.movieapp.models.NowPlaying;
import com.ardnn.movieapp.networks.Const;
import com.bumptech.glide.Glide;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<NowPlayingAdapter.ViewHolder> {
    private List<NowPlaying> nowPlayings;
    private OnItemClick onItemClick;

    public NowPlayingAdapter(List<NowPlaying> nowPlayings, OnItemClick onItemClick) {
        this.nowPlayings = nowPlayings;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_movie, parent, false);

        return new ViewHolder(view, onItemClick);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(holder.itemView.getContext())
                .load(Const.IMG_URL_200 + nowPlayings.get(position).getImageUrl())
                .into(holder.ivPoster);

        holder.tvTitle.setText(nowPlayings.get(position).getName());
        holder.tvVote.setText(String.valueOf(nowPlayings.get(position).getVote()));
        holder.tvYearReleased.setText(nowPlayings.get(position).getReleaseDate().substring(0, 4));
    }

    @Override
    public int getItemCount() {
        return nowPlayings.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        OnItemClick onItemClick;
        ImageView ivPoster;
        TextView tvTitle, tvYearReleased, tvVote;

        ViewHolder(@NonNull View itemView, OnItemClick onItemClick) {
            super(itemView);

            this.onItemClick = onItemClick;
            itemView.setOnClickListener(this);

            ivPoster = itemView.findViewById(R.id.iv_poster_item_movie);
            tvTitle = itemView.findViewById(R.id.tv_title_item_movie);
            tvVote = itemView.findViewById(R.id.tv_vote_item_movie);
            tvYearReleased = itemView.findViewById(R.id.tv_year_released_item_movie);

        }

        @Override
        public void onClick(View v) {
            onItemClick.onClick(getAdapterPosition());
        }
    }

    public interface OnItemClick {
        void onClick(int position);
    }
}
