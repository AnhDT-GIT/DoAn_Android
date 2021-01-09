package com.example.doan_android.Service;

public class APIService {
    private static String base_url = "https://tranquochung1711061818.000webhostapp.com/server/";

    public static DataService getService() {
        return APIRetrofit.getClient(base_url).create(DataService.class);
    }
}
