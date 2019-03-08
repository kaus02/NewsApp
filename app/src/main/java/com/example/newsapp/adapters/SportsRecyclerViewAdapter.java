package com.example.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.datastore.sports.SportsArticleEntity;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SportsRecyclerViewAdapter  extends RecyclerView.Adapter<SportsRecyclerViewAdapter.ViewHolder>{
    private List<SportsArticleEntity> articleList = new ArrayList<SportsArticleEntity>();
    private Context mContext;

    public interface OnRecyclerViewClickListener {
        void onClick(int position, View v);
    }

    private OnRecyclerViewClickListener clickListner;

    public SportsRecyclerViewAdapter(Context ctx, OnRecyclerViewClickListener clickListner) {
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
        SportsArticleEntity article = articleList.get(position);
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


    public void setArticleList(List<SportsArticleEntity> articleList) {
        this.articleList = articleList;
        notifyDataSetChanged();

    }

    public void appendArticleList(List<SportsArticleEntity> articleList){
//        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new MyDiffUtil(this.articleList, articleList));
//        diffResult.dispatchUpdatesTo(this);
        this.articleList.addAll(articleList);
        notifyDataSetChanged();
    }

    public List<SportsArticleEntity> getArticleList() {
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
