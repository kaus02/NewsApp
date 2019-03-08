package com.example.newsapp.datastore.business;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class BusinessArticleRepository {

    private BusinessArticleDao articleDao;
    private LiveData<List<BusinessArticleEntity>> articleList;



    public BusinessArticleRepository(Application application, String category){
        BusinessArticleDatabase articleDatabase = BusinessArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<BusinessArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<BusinessArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<BusinessArticleEntity>,Void,Void>{
        WeakReference<BusinessArticleRepository> articleRepositoryWeakReference;

        public InsertTask(BusinessArticleRepository businessArticleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<BusinessArticleRepository>(businessArticleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<BusinessArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
