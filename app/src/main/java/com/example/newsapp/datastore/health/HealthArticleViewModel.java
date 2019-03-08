package com.example.newsapp.datastore.health;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class HealthArticleViewModel extends AndroidViewModel {
    private HealthArticleRepository healthArticleRepository;
    private LiveData<List<HealthArticleEntity>> list;


    public HealthArticleViewModel(@NonNull Application application, String category) {
        super(application);
        healthArticleRepository = new HealthArticleRepository(application,category);
        list = healthArticleRepository.getArticleList();
    }

    public LiveData<List<HealthArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<HealthArticleEntity> articleEntity){
        healthArticleRepository.insertArticle(articleEntity);
    }
}
