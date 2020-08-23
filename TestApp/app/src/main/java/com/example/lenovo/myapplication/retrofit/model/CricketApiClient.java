package com.example.lenovo.myapplication.retrofit.model;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class CricketApiClient {

    private static final String CRICKET_BASE_URL = "https://cricapi.com/api/";
    public static final String API_KEY_CRICKET = "xtHqJY7jB6PeJzpRycNnMmPqxfp1";

    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(interceptor).build();


        Retrofit cricketApiClient = new Retrofit.Builder().baseUrl(CRICKET_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return cricketApiClient;


    }
}
