package com.example.newsapp.datastore.entertainment;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface EntertainmentArticleDao {

    @Query("select * from entertainment_article_table where category=:article")
    LiveData<List<EntertainmentArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<EntertainmentArticleEntity> article);
}
