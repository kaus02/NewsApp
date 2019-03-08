package com.example.newsapp.network;

import android.content.Context;

import com.example.newsapp.network.ITopHeadlines;
import com.example.newsapp.network.RetrofitClient;

import retrofit2.Retrofit;

public final class TopHeadlinesService {
    public static ITopHeadlines getInstance(Context ctx){
        return RetrofitClient.getInstance(ctx).create(ITopHeadlines.class);
    }
}
