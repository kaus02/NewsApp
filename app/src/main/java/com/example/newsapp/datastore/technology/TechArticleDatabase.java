package com.example.newsapp.datastore.technology;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TechArticleEntity.class}, version = 1,exportSchema = false)
public abstract class TechArticleDatabase extends RoomDatabase {

    public abstract TechArticleDao getArticleDao();

    private static TechArticleDatabase mInstance;

    public static TechArticleDatabase getInstance(Context ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    TechArticleDatabase.class,"tech_article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
