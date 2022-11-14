package com.example.myapplication.network.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetCurrentWeatherResponse {
    @SerializedName("count")
    private int count;
    @SerializedName("data")
    private List<CurrentWeather> currentWeathers;

    public int getCount() {
        return count;
    }

    public List<CurrentWeather> getCurrentWeathers() {
        return currentWeathers;
    }

    public class CurrentWeather {
        @SerializedName("temp")
        private double temp;
        @SerializedName("app_temp")
        private double feelLikeTemp;
        @SerializedName("city_name")
        private String cityName;

        public double getTemp() {
            return temp;
        }

        public double getFeelLikeTemp() {
            return feelLikeTemp;
        }

        public String getCityName() {
            return cityName;
        }
    }
}
