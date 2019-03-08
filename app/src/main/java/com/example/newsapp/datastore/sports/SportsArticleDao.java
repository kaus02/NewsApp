package com.example.newsapp.datastore.sports;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface SportsArticleDao {

    @Query("select * from sports_article_table where category=:article")
    LiveData<List<SportsArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<SportsArticleEntity> article);
}
