package com.example.newsapp.datastore.home;

import android.app.Application;

import com.example.newsapp.datastore.home.ArticleViewModel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyViewModelFactory implements ViewModelProvider.Factory {
    private Application mApplication;
    private String mParam;


    public MyViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }


    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new ArticleViewModel(mApplication, mParam);
    }
}
