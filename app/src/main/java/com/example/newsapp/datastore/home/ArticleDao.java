package com.example.newsapp.datastore.home;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ArticleDao {

    @Query("select * from article_table where category=:article")
    LiveData<List<ArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<ArticleEntity> article);
}
