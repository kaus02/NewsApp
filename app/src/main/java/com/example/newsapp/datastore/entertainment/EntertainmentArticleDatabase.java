package com.example.newsapp.datastore.entertainment;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {EntertainmentArticleEntity.class}, version = 1,exportSchema = false)
public abstract class EntertainmentArticleDatabase extends RoomDatabase {

    public abstract EntertainmentArticleDao getArticleDao();

    private static EntertainmentArticleDatabase mInstance;

    public static EntertainmentArticleDatabase getInstance(Context ctx){
        if(mInstance==null){
            mInstance = Room.databaseBuilder(ctx.getApplicationContext(),
                    EntertainmentArticleDatabase.class,"entertainment_article_info")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return mInstance;
    }
}
