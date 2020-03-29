package com.example.mnallamalli97.lahacks2020.utilities;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkUtils {

    private static Retrofit retrofit;
    private static final String BASE_URL = "http://35.236.56.170:80/";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static GamifyClient getGamifyClient() {
        GamifyClient client =  getRetrofitInstance().create(GamifyClient.class);
        return client;
    }

}
