package com.example.newsapp.datastore.home;

import android.app.Application;
import android.os.AsyncTask;

import com.example.newsapp.AppExecutors;

import java.lang.ref.WeakReference;
import java.util.List;

import androidx.lifecycle.LiveData;

public class ArticleRepository {

    private ArticleDao articleDao;
    private LiveData<List<ArticleEntity>> articleList;



    public ArticleRepository(Application application, String category){
        ArticleDatabase articleDatabase = ArticleDatabase.getInstance(application);
        articleDao = articleDatabase.getArticleDao();
        articleList = articleDao.getArticles(category);
    }

    public LiveData<List<ArticleEntity>> getArticleList() {
        return articleList;
    }

    public void insertArticle(List<ArticleEntity> article){
        AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                articleDao.insertArticles(article);
            }
        });
//        new InsertTask(this).execute(article);
    }

    private class InsertTask extends AsyncTask<List<ArticleEntity>,Void,Void>{
        WeakReference<ArticleRepository> articleRepositoryWeakReference;

        public InsertTask(ArticleRepository articleRepositoryReference) {
            articleRepositoryWeakReference = new WeakReference<ArticleRepository>(articleRepositoryReference);
        }

        @Override
        protected Void doInBackground(List<ArticleEntity>... lists) {
            articleRepositoryWeakReference.get().articleDao.insertArticles(lists[0]);
            return null;
        }
    }


}
