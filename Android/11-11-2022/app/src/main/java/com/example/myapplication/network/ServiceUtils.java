package com.example.myapplication.network;

public class ServiceUtils {
    private GetServices getServices;
    private static ServiceUtils instance;

    private ServiceUtils() {
        getServices = ServiceFactory.getInstance().createService(GetServices.class);
    }

    public static ServiceUtils getInstance() {
        if(instance == null) {
            instance = new ServiceUtils();
        }
        return instance;
    }

    public GetServices getGetServices() {
        return getServices;
    }
}
