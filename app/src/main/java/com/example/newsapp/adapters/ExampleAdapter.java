package com.example.newsapp.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.newsapp.datastore.home.ArticleEntity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class ExampleAdapter extends ListAdapter<ArticleEntity, ExampleAdapter.UserViewHolder> {

    protected ExampleAdapter(@NonNull DiffUtil.ItemCallback<ArticleEntity> diffCallback) {
        super(diffCallback);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
