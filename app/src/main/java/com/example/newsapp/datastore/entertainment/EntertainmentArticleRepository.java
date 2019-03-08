package com.example.newsapp.datastore.entertainment;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class EntertainmentArticleRepository {

    private EntertainmentArticleDao articleDao;
    private LiveData<List<EntertainmentArticleEntity>> articleList;



    public EntertainmentArticleRepository(Application application, String category){
        EntertainmentArticleDatabase articleDatabase = EntertainmentArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<EntertainmentArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<EntertainmentArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<EntertainmentArticleEntity>,Void,Void>{
        WeakReference<EntertainmentArticleRepository> articleRepositoryWeakReference;

        public InsertTask(EntertainmentArticleRepository entertainmentArticleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<EntertainmentArticleRepository>(entertainmentArticleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<EntertainmentArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
