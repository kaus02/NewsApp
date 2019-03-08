package com.example.newsapp.datastore.technology;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface TechArticleDao {

    @Query("select * from tech_article_table where category=:article")
    LiveData<List<TechArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<TechArticleEntity> article);
}
