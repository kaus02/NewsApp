package com.example.newsapp.datastore.science;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class ScienceArticleRepository {

    private ScienceArticleDao articleDao;
    private LiveData<List<ScienceArticleEntity>> articleList;



    public ScienceArticleRepository(Application application, String category){
        ScienceArticleDatabase articleDatabase = ScienceArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<ScienceArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<ScienceArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<ScienceArticleEntity>,Void,Void>{
        WeakReference<ScienceArticleRepository> articleRepositoryWeakReference;

        public InsertTask(ScienceArticleRepository scienceArticleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<ScienceArticleRepository>(scienceArticleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<ScienceArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
