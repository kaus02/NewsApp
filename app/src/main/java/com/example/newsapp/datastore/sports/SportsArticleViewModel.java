package com.example.newsapp.datastore.sports;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class SportsArticleViewModel extends AndroidViewModel {
    private SportsArticleRepository sportsArticleRepository;
    private LiveData<List<SportsArticleEntity>> list;


    public SportsArticleViewModel(@NonNull Application application, String category) {
        super(application);
        sportsArticleRepository = new SportsArticleRepository(application,category);
        list = sportsArticleRepository.getArticleList();
    }

    public LiveData<List<SportsArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<SportsArticleEntity> articleEntity){
        sportsArticleRepository.insertArticle(articleEntity);
    }
}
