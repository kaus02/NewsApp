package com.example.newsapp;

import com.example.newsapp.datastore.home.ArticleEntity;

import java.util.List;

import androidx.recyclerview.widget.DiffUtil;

public class MyDiffUtil extends DiffUtil.Callback {

    List<ArticleEntity> oldArticles;
    List<ArticleEntity> newArticles;

    public MyDiffUtil(List<ArticleEntity> newArticles, List<ArticleEntity> oldArticles) {
        this.newArticles = newArticles;
        this.oldArticles = oldArticles;
    }

    @Override
    public int getOldListSize() {
        return oldArticles.size();
    }

    @Override
    public int getNewListSize() {
        return newArticles.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return true;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldArticles.get(oldItemPosition).getContent()==newArticles.get(newItemPosition).getContent();
    }
}
