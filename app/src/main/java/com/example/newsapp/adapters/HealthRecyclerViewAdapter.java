package com.example.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.datastore.health.HealthArticleEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HealthRecyclerViewAdapter extends RecyclerView.Adapter<HealthRecyclerViewAdapter.ViewHolder>{
    private List<HealthArticleEntity> articleList = new ArrayList<HealthArticleEntity>();
    private Context mContext;

    public interface OnRecyclerViewClickListener {
        void onClick(int position, View v);
    }

    private OnRecyclerViewClickListener clickListner;

    public HealthRecyclerViewAdapter(Context ctx, OnRecyclerViewClickListener clickListner) {
        mContext = ctx;
        this.clickListner = clickListner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_xml,parent,false);
        return new ViewHolder(root);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        HealthArticleEntity article = articleList.get(position);
        Glide.with(mContext)
                .load(article.getUrlToImage())
                .into( holder.articleImage);
        holder.articleTitle.setText(article.getTitle());
        holder.articleDescription.setText(article.getDescription());


    }

    @Override
    public int getItemCount() {
        return articleList.size();
    }


    public void setArticleList(List<HealthArticleEntity> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();

    }

    public void appendArticleList(List<HealthArticleEntity> articleList){
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtil(this.articleList, articleList));
//        diffResult.dispatchUpdatesTo(this);
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public List<HealthArticleEntity> getArticleList() {
        return articleList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.articleImage)
        ImageView articleImage;

        @BindView(R.id.articleTitle)
        TextView articleTitle;

        @BindView(R.id.articleDescription)
        TextView articleDescription;

        public ViewHolder(@NonNull View itemView) {
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
