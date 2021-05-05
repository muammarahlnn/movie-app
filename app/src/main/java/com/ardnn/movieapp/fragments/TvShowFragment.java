package com.ardnn.movieapp.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ardnn.movieapp.R;
import com.ardnn.movieapp.activities.DetailActivity;
import com.ardnn.movieapp.activities.MainActivity;
import com.ardnn.movieapp.adapters.AiringTodayAdapter;
import com.ardnn.movieapp.models.AiringToday;
import com.ardnn.movieapp.models.AiringTodayResponse;
import com.ardnn.movieapp.networks.Const;
import com.ardnn.movieapp.networks.TvShowApiClient;
import com.ardnn.movieapp.networks.TvShowApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowFragment extends Fragment implements AiringTodayAdapter.OnItemClick {
    private RecyclerView rvTvShows;
    private AiringTodayAdapter airingTodayAdapter;
    private List<AiringToday> airingTodayList;

    public static TvShowFragment newInstance() {
        TvShowFragment fragment = new TvShowFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_STRING, "TV Shows");
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        rvTvShows = view.findViewById(R.id.rv_tv_shows);
        rvTvShows.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        loadData();

        return view;
    }

    private void loadData() {
        TvShowApiInterface tvShowApiInterface = TvShowApiClient.getRetrofit()
                .create(TvShowApiInterface.class);

        Call<AiringTodayResponse> airingTodayResponseCall = tvShowApiInterface.getAiringToday(Const.API_KEY);
        airingTodayResponseCall.enqueue(new Callback<AiringTodayResponse>() {
            @Override
            public void onResponse(Call<AiringTodayResponse> call, Response<AiringTodayResponse> response) {
                if (response.isSuccessful() && response.body().getAiringTodayList() != null) {
                    airingTodayList = response.body().getAiringTodayList();
                    airingTodayAdapter = new AiringTodayAdapter(airingTodayList, TvShowFragment.this);
                    rvTvShows.setAdapter(airingTodayAdapter);
                } else {
                    Toast.makeText(getActivity(), "Response failed.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<AiringTodayResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Response failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onclick(int position) {
        Intent goToDetail = new Intent(getActivity(), DetailActivity.class);
        goToDetail.putExtra(DetailActivity.EXTRA_TITLE, airingTodayList.get(position).getTitle());
        goToDetail.putExtra(DetailActivity.EXTRA_SYNOPSIS, airingTodayList.get(position).getSynopsis());
        goToDetail.putExtra(DetailActivity.EXTRA_IMAGE_URL, airingTodayList.get(position).getImageUrl());
        goToDetail.putExtra(DetailActivity.EXTRA_RELEASE_DATE, airingTodayList.get(position).getFirstAiring());
        goToDetail.putExtra(DetailActivity.EXTRA_VOTE, airingTodayList.get(position).getVote());
        startActivity(goToDetail);
    }
}