package com.example.newsapp.datastore.technology;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TechArticleViewModel extends AndroidViewModel {
    private TechArticleRepository techArticleRepository;
    private LiveData<List<TechArticleEntity>> list;


    public TechArticleViewModel(@NonNull Application application, String category) {
        super(application);
        techArticleRepository = new TechArticleRepository(application,category);
        list = techArticleRepository.getArticleList();
    }

    public LiveData<List<TechArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<TechArticleEntity> articleEntity){
        techArticleRepository.insertArticle(articleEntity);
    }
}
