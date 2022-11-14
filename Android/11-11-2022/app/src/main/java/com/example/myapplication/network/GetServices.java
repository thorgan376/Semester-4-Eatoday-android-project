package com.example.myapplication.network;

import com.example.myapplication.network.model.GetCurrentWeatherResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetServices {
    @GET(API.API_GET_CURRENT_WHEATHER)
    Call<GetCurrentWeatherResponse> requestGetCurrentWeather(@Query("key") String apiKey,
                                                             @Query("lat") double latitude,
                                                             @Query("lon") double longitude);
}
