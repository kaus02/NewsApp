package com.example.newsapp.fragments;

import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;

import com.example.newsapp.ApiCallback;
import com.example.newsapp.R;
import com.example.newsapp.adapters.RecyclerViewAdapter;
import com.example.newsapp.datastore.home.ArticleEntity;
import com.example.newsapp.datastore.home.ArticleViewModel;
import com.example.newsapp.datastore.home.MyViewModelFactory;
import com.example.newsapp.network.TopHeadlinesService;
import com.example.newsapp.network.models.Articles;
import com.example.newsapp.network.models.HeadlineResponse;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements RecyclerViewAdapter.OnRecyclerViewClickListener {

    private  long PAGE_SIZE = 20;
    private long PAGE_INDEX = 1;
    private long TOTAL_ARTICLES;

    private ArticleViewModel articleViewModel;
    private RecyclerViewAdapter adapter;
    private boolean loadMoreData = true;
    private boolean nextRequested = false;
    private boolean isScrolling = false;
    private boolean tabOpened = false;

    private List<ArticleEntity> articleEntityListMain = new ArrayList<>();

    @BindView(R.id.recyclerView)
    RecyclerView rv;

    @BindView(R.id.refreshLayout)
    SwipeRefreshLayout refreshLayout;

    private HashSet hashList = new HashSet();
    private int totalItemCount;
    AtomicBoolean aBoolean = new AtomicBoolean(true);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.home_fragment, container, false);
        ButterKnife.bind(this, root);
        articleViewModel = ViewModelProviders.of(this, new MyViewModelFactory(getActivity().getApplication() ,"")).get(ArticleViewModel.class);

        adapter = new RecyclerViewAdapter(getContext(),this);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(linearLayoutManager);
        rv.setAdapter(adapter);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState== AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL)
                    isScrolling = true;
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
                nextRequested = true;
                if(aBoolean.get() && isScrolling && loadMoreData && nextRequested){
                    if((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                            && firstVisibleItemPosition >= 0
                            && totalItemCount >= PAGE_SIZE) {
                        aBoolean.compareAndSet(true,false);
                        isScrolling = false;
                        Snackbar.make(getView(),"Fetching more data",Snackbar.LENGTH_LONG).show();
                        loadData();


                    }
                }
            }
        });

        articleViewModel.getList().observe(this, new Observer<List<ArticleEntity>>() {
            @Override
            public void onChanged(List<ArticleEntity> articleEntities) {
                if(articleEntities.size()>0){
                    adapter.setArticleList(articleEntities);
                    articleEntityListMain.clear();
                }
            }
        });
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout.setRefreshing(false);
            }
        });
        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(loadMoreData && !tabOpened){
            loadData();
        }
        else if(tabOpened){
            tabOpened = false;
        }
    }

      private void loadData() {
          fetchData(new ApiCallback() {
              @Override
              public void onSuccess(HeadlineResponse headlineResponse) {
                  List<ArticleEntity> list = new ArrayList<>();
                  for(Articles articles : headlineResponse.getArticles()){
                      list.add(new ArticleEntity(articles.getAuthor(),
                                                articles.getTitle(),
                              articles.getDescription(),
                              articles.getUrl(),
                              articles.getUrlToImage(),
                              articles.getPublishedAt(),
                              articles.getContent(),
                              articles.getSource().get("name"),""));
                  }
                  adapter.appendArticleList(list);
                  articleEntityListMain.addAll(list);
                  TOTAL_ARTICLES = headlineResponse.getTotalResults();
                  if(PAGE_SIZE*PAGE_INDEX>=TOTAL_ARTICLES){
                      loadMoreData = false;
                      articleViewModel.insertArticle(articleEntityListMain);
                      PAGE_INDEX = 1;
                  }else{
                      PAGE_INDEX++;
                  }
              }

              @Override
              public void onError(String mess) {

              }
          });

      }



    private void fetchData(final ApiCallback callback) {
        Map<String, String> mp = new HashMap<>();
        mp.put("country", "us");
        mp.put("page", String.valueOf(PAGE_INDEX));
        mp.put("pageSize", String.valueOf(PAGE_SIZE));
        Call<HeadlineResponse> response = TopHeadlinesService.getInstance(getActivity()).getTopHeadlines(mp);
                response.enqueue(new Callback<HeadlineResponse>() {
                    @Override
                    public void onResponse(Call<HeadlineResponse> call, Response<HeadlineResponse> response) {
                        aBoolean.compareAndSet(false,true);
                        callback.onSuccess(response.body());
                    }

                    @Override
                    public void onFailure(Call<HeadlineResponse> call, Throwable t) {

                    }
                });
//
    }

    @Override
    public void onClick(int position, View v) {
        ArticleEntity article = adapter.getArticleList().get(position);
        CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
        CustomTabsIntent customTabsIntent = builder.build();
        tabOpened = true;
        customTabsIntent.launchUrl(getContext(), Uri.parse(article.getUrl()));
    }
}
