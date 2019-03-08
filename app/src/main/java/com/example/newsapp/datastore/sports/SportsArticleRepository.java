package com.example.newsapp.datastore.sports;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class SportsArticleRepository {

    private SportsArticleDao articleDao;
    private LiveData<List<SportsArticleEntity>> articleList;



    public SportsArticleRepository(Application application, String category){
        SportsArticleDatabase articleDatabase = SportsArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<SportsArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<SportsArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<SportsArticleEntity>,Void,Void>{
        WeakReference<SportsArticleRepository> articleRepositoryWeakReference;

        public InsertTask(SportsArticleRepository sportsArticleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<SportsArticleRepository>(sportsArticleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<SportsArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
