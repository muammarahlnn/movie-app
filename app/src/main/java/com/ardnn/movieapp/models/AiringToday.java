package com.ardnn.movieapp.models;

import com.google.gson.annotations.SerializedName;

public class AiringToday {
    @SerializedName("name")
    private String title;

    @SerializedName("poster_path")
    private String imageUrl;

    @SerializedName("overview")
    private String synopsis;

    @SerializedName("first_air_date")
    private String firstAiring;

    @SerializedName("vote_average")
    private double vote;

    // getter and setter -------------------------------
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getFirstAiring() {
        return firstAiring;
    }

    public void setFirstAiring(String firstAiring) {
        this.firstAiring = firstAiring;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }
}
