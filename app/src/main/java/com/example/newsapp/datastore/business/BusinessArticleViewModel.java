package com.example.newsapp.datastore.business;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class BusinessArticleViewModel extends AndroidViewModel {
    private BusinessArticleRepository businessArticleRepository;
    private LiveData<List<BusinessArticleEntity>> list;


    public BusinessArticleViewModel(@NonNull Application application, String category) {
        super(application);
        businessArticleRepository = new BusinessArticleRepository(application,category);
        list = businessArticleRepository.getArticleList();
    }

    public LiveData<List<BusinessArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<BusinessArticleEntity> articleEntity){
        businessArticleRepository.insertArticle(articleEntity);
    }
}
