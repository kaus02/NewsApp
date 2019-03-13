package com.example.newsapp.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.newsapp.R;
import com.example.newsapp.datastore.home.ArticleEntity;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlePagedListAdapter extends PagedListAdapter<ArticleEntity, ArticlePagedListAdapter.ArticleViewHolder> {

    private List<ArticleEntity> articleList = new ArrayList<>();
    private Context mContext;

    public interface OnRecyclerViewClickListener {
        void onClick(int position, View v);
    }

    private OnRecyclerViewClickListener clickListner;

    public ArticlePagedListAdapter(Context ctx, OnRecyclerViewClickListener clickListner) {
        super(DIFF_CALLBACK);
        mContext = ctx;
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_xml,parent,false);
        return new ArticleViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        ArticleEntity article = articleList.get(position);

        Glide.with(mContext)
                .load(article.getUrlToImage())
                .into(holder.articleImage);
        holder.articleTitle.setText(article.getTitle());
        holder.articleDescription.setText(article.getDescription());
    }

//    public void write(String fileName, Bitmap bitmap) {
//        FileOutputStream outputStream;
//        try {
//            outputStream = mContext.openFileOutput(fileName, Context.MODE_PRIVATE);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
//            outputStream.close();
//        } catch (Exception error) {
//            error.printStackTrace();
//        }
//    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public void setArticleList(List<ArticleEntity> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();

    }

    public void appendArticleList(List<ArticleEntity> articleList){
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public List<ArticleEntity> getArticleList() {
        return articleList;
    }

    private static DiffUtil.ItemCallback<ArticleEntity> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<ArticleEntity>() {
                @Override
                public boolean areItemsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
                    return false;
                }

                @Override
                public boolean areContentsTheSame(@NonNull ArticleEntity oldItem, @NonNull ArticleEntity newItem) {
                    return oldItem.equals(newItem);

                }
            };

    public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.articleImage)
        ImageView articleImage;

        @BindView(R.id.articleTitle)
        TextView articleTitle;

        @BindView(R.id.articleDescription)
        TextView articleDescription;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListner.onClick(getAdapterPosition(),v);
        }
    }
}
