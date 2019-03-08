package com.example.newsapp.datastore.entertainment;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class EntertainmentArticleViewModel extends AndroidViewModel {
    private EntertainmentArticleRepository entertainmentArticleRepository;
    private LiveData<List<EntertainmentArticleEntity>> list;


    public EntertainmentArticleViewModel(@NonNull Application application, String category) {
        super(application);
        entertainmentArticleRepository = new EntertainmentArticleRepository(application,category);
        list = entertainmentArticleRepository.getArticleList();
    }

    public LiveData<List<EntertainmentArticleEntity>> getList() {
        return list;
    }

    public void insertArticle(List<EntertainmentArticleEntity> articleEntity){
        entertainmentArticleRepository.insertArticle(articleEntity);
    }
}
