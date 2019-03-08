package com.example.newsapp.datastore.science;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ScienceArticleEntity.class}, version = 1,exportSchema = false)
public abstract class ScienceArticleDatabase extends RoomDatabase {

    public abstract ScienceArticleDao getArticleDao();

    private static ScienceArticleDatabase mInstance;

    public static ScienceArticleDatabase getInstance(Context ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    ScienceArticleDatabase.class,"science_article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
