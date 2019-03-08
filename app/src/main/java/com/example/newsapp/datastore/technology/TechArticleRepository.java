package com.example.newsapp.datastore.technology;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class TechArticleRepository {

    private TechArticleDao articleDao;
    private LiveData<List<TechArticleEntity>> articleList;



    public TechArticleRepository(Application application, String category){
        TechArticleDatabase articleDatabase = TechArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<TechArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<TechArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<TechArticleEntity>,Void,Void>{
        WeakReference<TechArticleRepository> articleRepositoryWeakReference;

        public InsertTask(TechArticleRepository techArticleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<TechArticleRepository>(techArticleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<TechArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
