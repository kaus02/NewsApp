package com.example.newsapp.datastore.health;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class HealthArticleRepository {

    private HealthArticleDao articleDao;
    private LiveData<List<HealthArticleEntity>> articleList;



    public HealthArticleRepository(Application application, String category){
        HealthArticleDatabase articleDatabase = HealthArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<HealthArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<HealthArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<HealthArticleEntity>,Void,Void>{
        WeakReference<HealthArticleRepository> articleRepositoryWeakReference;

        public InsertTask(HealthArticleRepository healthArticleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<HealthArticleRepository>(healthArticleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<HealthArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
