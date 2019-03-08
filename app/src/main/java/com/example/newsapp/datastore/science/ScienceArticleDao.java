package com.example.newsapp.datastore.science;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface ScienceArticleDao {

    @Query("select * from science_article_table where category=:article")
    LiveData<List<ScienceArticleEntity>> getArticles(String article);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticles(List<ScienceArticleEntity> article);
}
