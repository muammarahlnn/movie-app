package com.ardnn.movieapp.networks;

import com.ardnn.movieapp.models.AiringTodayResponse;
import com.ardnn.movieapp.models.NowPlayingResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieApiInterface {
    @GET("now_playing")
    Call<NowPlayingResponse> getNowPlaying(
            @Query("api_key") String apiKey
    );
}
