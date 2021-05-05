package com.ardnn.movieapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ardnn.movieapp.R;
import com.ardnn.movieapp.activities.DetailActivity;
import com.ardnn.movieapp.activities.MainActivity;
import com.ardnn.movieapp.adapters.NowPlayingAdapter;
import com.ardnn.movieapp.models.NowPlaying;
import com.ardnn.movieapp.models.NowPlayingResponse;
import com.ardnn.movieapp.networks.Const;
import com.ardnn.movieapp.networks.MovieApiClient;
import com.ardnn.movieapp.networks.MovieApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieFragment extends Fragment implements NowPlayingAdapter.OnItemClick {
    private RecyclerView rvMovies;
    private NowPlayingAdapter nowPlayingAdapter;
    private List<NowPlaying> nowPlayings;

    public static MovieFragment newInstance() {
        MovieFragment fragment = new MovieFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_STRING, "Movies");
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_movie, container, false);
        rvMovies = view.findViewById(R.id.rv_movies);
        rvMovies.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        loadData();
        return view;
    }

    private void loadData() {
        MovieApiInterface movieApiInterface = MovieApiClient.getRetrofit()
                .create(MovieApiInterface.class);

        Call<NowPlayingResponse> nowPlayingResponseCall = movieApiInterface.getNowPlaying(Const.API_KEY);
        nowPlayingResponseCall.enqueue(new Callback<NowPlayingResponse>() {
            @Override
            public void onResponse(Call<NowPlayingResponse> call, Response<NowPlayingResponse> response) {
                if (response.isSuccessful() && response.body().getNowPlayings() != null) {
                    nowPlayings = response.body().getNowPlayings();
                    nowPlayingAdapter = new NowPlayingAdapter(nowPlayings, MovieFragment.this);
                    rvMovies.setAdapter(nowPlayingAdapter);
                } else {
                    Toast.makeText(getActivity(), "Response failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NowPlayingResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Response Failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(int position) {
        Intent goToDetail = new Intent(getActivity(), DetailActivity.class);
        goToDetail.putExtra(DetailActivity.EXTRA_TITLE, nowPlayings.get(position).getTitle());
        goToDetail.putExtra(DetailActivity.EXTRA_SYNOPSIS, nowPlayings.get(position).getSynopsis());
        goToDetail.putExtra(DetailActivity.EXTRA_IMAGE_URL, nowPlayings.get(position).getImageUrl());
        goToDetail.putExtra(DetailActivity.EXTRA_RELEASE_DATE, nowPlayings.get(position).getReleaseDate());
        goToDetail.putExtra(DetailActivity.EXTRA_VOTE, nowPlayings.get(position).getVote());
        startActivity(goToDetail);

    }
}