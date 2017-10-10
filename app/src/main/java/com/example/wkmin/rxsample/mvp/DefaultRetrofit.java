package com.example.wkmin.rxsample.mvp;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by wk.min on 29/09/2017.
 * DefaultRetrofit
 */

class DefaultRetrofit {
    Retrofit invoke() {
        return new Retrofit.Builder()
                .client(new OkHttpClient())
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
}