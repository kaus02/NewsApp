package com.example.newsapp.datastore.health;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface HealthArticleDao {

    @Query("select * from health_article_table where category=:article")
    LiveData<List<HealthArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<HealthArticleEntity> article);
}
