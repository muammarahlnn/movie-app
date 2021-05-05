package com.ardnn.movieapp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AiringTodayResponse {
    @SerializedName("results")
    @Expose
    private List<AiringToday> airingTodayList;

    public List<AiringToday> getAiringTodayList() {
        return airingTodayList;
    }
}
