package com.example.newsapp.datastore.business;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface BusinessArticleDao {

    @Query("select * from business_article_table where category=:article")
    LiveData<List<BusinessArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<BusinessArticleEntity> article);
}
