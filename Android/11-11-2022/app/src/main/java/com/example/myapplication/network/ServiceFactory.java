package com.example.myapplication.network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceFactory {

    private Retrofit retrofit;
    private static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            instance = new ServiceFactory();
        }
        return instance;
    }

    private ServiceFactory() {
        retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public <ServiceClass> ServiceClass createService(Class<ServiceClass> serviceClass) {
        return retrofit.create(serviceClass);
    }
}
