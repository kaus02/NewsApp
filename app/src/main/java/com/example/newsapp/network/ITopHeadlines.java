package com.example.newsapp.network;

import com.example.newsapp.network.models.HeadlineResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface ITopHeadlines {
    @GET("/v2/top-headlines")
    Call<HeadlineResponse> getTopHeadlines(@QueryMap Map<String,String> stringMap
//                                           @Query("page") long page,
//                                           @Query("pageSize") long pageSize
 );
}
