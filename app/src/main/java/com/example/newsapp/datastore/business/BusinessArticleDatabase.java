package com.example.newsapp.datastore.business;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BusinessArticleEntity.class}, version = 1,exportSchema = false)
public abstract class BusinessArticleDatabase extends RoomDatabase {

    public abstract BusinessArticleDao getArticleDao();

    private static BusinessArticleDatabase mInstance;

    public static BusinessArticleDatabase getInstance(Context ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    BusinessArticleDatabase.class,"business_article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
