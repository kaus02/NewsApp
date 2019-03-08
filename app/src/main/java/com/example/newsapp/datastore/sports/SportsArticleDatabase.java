package com.example.newsapp.datastore.sports;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {SportsArticleEntity.class}, version = 1,exportSchema = false)
public abstract class SportsArticleDatabase extends RoomDatabase {

    public abstract SportsArticleDao getArticleDao();

    private static SportsArticleDatabase mInstance;

    public static SportsArticleDatabase getInstance(Context ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    SportsArticleDatabase.class,"sports_article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
