package com.example.newsapp;

import com.example.newsapp.network.models.HeadlineResponse;

public interface ApiCallback {
    public void onSuccess(HeadlineResponse headlineResponse);
    public void onError(String mess);

}
