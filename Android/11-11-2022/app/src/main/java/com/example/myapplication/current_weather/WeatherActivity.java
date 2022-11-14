package com.example.myapplication.current_weather;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.network.API;
import com.example.myapplication.network.ServiceUtils;
import com.example.myapplication.network.model.GetCurrentWeatherResponse;
import com.google.gson.Gson;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherActivity extends AppCompatActivity {
    private static final String TAG = WeatherActivity.class.getSimpleName();
    private static final String API_KEY = "8d37a1870c804c5b992b24a05dd458b3";
    private double latitude = 21.027305339413203;
    private double longitude = 105.83018292346364;

    @BindView(R.id.edt_latitude)
    EditText edtLatitude;
    @BindView(R.id.edt_longitude)
    EditText edtLongitude;
    @BindView(R.id.txt_city_name)
    TextView txtCityName;
    @BindView(R.id.txt_temp)
    TextView txtTemp;
    @BindView(R.id.txt_feel_likes)
    TextView txtFeelLikes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);

        configView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        requestGetCurrentWeather();
    }

    private void configView() {
        ButterKnife.bind(this);
    }

    private void requestGetCurrentWeather() {
        Call<GetCurrentWeatherResponse> call = ServiceUtils.getInstance()
                .getGetServices().requestGetCurrentWeather(API_KEY, latitude, longitude);
        call.enqueue(new Callback<GetCurrentWeatherResponse>() {
            @Override
            public void onResponse(Call<GetCurrentWeatherResponse> call, Response<GetCurrentWeatherResponse> response) {
                if(response.code() == 200 && response.body() != null) {
                    GetCurrentWeatherResponse currentWeatherResponse = response.body();
                    if(currentWeatherResponse.getCurrentWeathers() != null
                            && currentWeatherResponse.getCurrentWeathers().size() != 0) {
                        GetCurrentWeatherResponse.CurrentWeather currentWeather
                                = currentWeatherResponse.getCurrentWeathers().get(0);

                        txtCityName.setText(String.valueOf(currentWeather.getCityName()));
                        txtTemp.setText(String.format(Locale.getDefault(), "%.0f℃", currentWeather.getTemp()));
                        txtFeelLikes.setText(String.format(Locale.getDefault(), "Nhiệt độ cảm nhận %.1f℃", currentWeather.getFeelLikeTemp()));
                    }

                }
                Log.e(TAG, "onResponse: statusCode: " + response.code() );
                Log.e(TAG, "onResponse: responseBody: " + new Gson().toJson(response.body()));
            }

            @Override
            public void onFailure(Call<GetCurrentWeatherResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getLocalizedMessage() );
            }
        });
    }
}