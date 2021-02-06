package com.pas.rastatask.API;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build();
                    return chain.proceed(newRequest);
                })
                .addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .baseUrl("https://rastatask.pasandsoft.ir")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

}

