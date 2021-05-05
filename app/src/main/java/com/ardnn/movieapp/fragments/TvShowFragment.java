package com.ardnn.movieapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ardnn.movieapp.R;
import com.ardnn.movieapp.activities.MainActivity;

public class TvShowFragment extends Fragment {

    public static TvShowFragment newInstance() {
        TvShowFragment fragment = new TvShowFragment();
        Bundle args = new Bundle();
        args.putString(MainActivity.EXTRA_STRING, "Tv Show");
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }
}