package com.example.newsapp.datastore.home;


import android.app.Application;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ArticleEntity.class}, version = 1,exportSchema = false)
public abstract class ArticleDatabase extends RoomDatabase {

    public abstract ArticleDao getArticleDao();

    private static ArticleDatabase mInstance;

    public static ArticleDatabase getInstance(Application ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx,
                    ArticleDatabase.class,"article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
