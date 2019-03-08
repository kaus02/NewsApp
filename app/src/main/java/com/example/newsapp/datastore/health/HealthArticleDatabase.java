package com.example.newsapp.datastore.health;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {HealthArticleEntity.class}, version = 1,exportSchema = false)
public abstract class HealthArticleDatabase extends RoomDatabase {

    public abstract HealthArticleDao getArticleDao();

    private static HealthArticleDatabase mInstance;

    public static HealthArticleDatabase getInstance(Context ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    HealthArticleDatabase.class,"health_article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
