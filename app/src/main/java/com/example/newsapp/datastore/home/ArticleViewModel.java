package com.example.newsapp.datastore.home;

import android.app.Application;

import com.example.newsapp.datastore.home.ArticleEntity;
import com.example.newsapp.datastore.home.ArticleRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class ArticleViewModel extends AndroidViewModel {
    private ArticleRepository articleRepository;
    private LiveData<List<ArticleEntity>> list;


    public ArticleViewModel(@NonNull Application application, String category) {
        super(application);
        articleRepository = new ArticleRepository(application,category);
        list = articleRepository.getArticleList();
    }

    public LiveData<List<ArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<ArticleEntity> articleEntity){
        articleRepository.insertArticle(articleEntity);
    }
}
