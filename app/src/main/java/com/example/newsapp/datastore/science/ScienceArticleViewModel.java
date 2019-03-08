package com.example.newsapp.datastore.science;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ScienceArticleViewModel extends AndroidViewModel {
    private ScienceArticleRepository scienceArticleRepository;
    private LiveData<List<ScienceArticleEntity>> list;


    public ScienceArticleViewModel(@NonNull Application application, String category) {
        super(application);
        scienceArticleRepository = new ScienceArticleRepository(application,category);
        list = scienceArticleRepository.getArticleList();
    }

    public LiveData<List<ScienceArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<ScienceArticleEntity> articleEntity){
        scienceArticleRepository.insertArticle(articleEntity);
    }
}
