package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;

import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import adapter.NewsAdapter;
import apis.NewsListApi;
import model.NewsModel;
import utils.Storage;
import variables.CommonVariable;

public class NewsList extends AppCompatActivity {

    private ListView list_view;
    private Storage storage;
    private SwipyRefreshLayout srl;
    private HashMap<String,String> source;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        list_view = findViewById(R.id.news_list);

        storage = new Storage(NewsList.this);
        source = storage.getSource();
        getView();

        TextView tv = findViewById(R.id.search_source_list);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewsList.this, SearchCategoryList.class));
            }
        });

        TextView navTitle = findViewById(R.id.title);
        if (source.get(Storage.SOURCE__NAME) != null) {
            navTitle.setText("News - " + source.get(Storage.SOURCE__NAME));
        }

        srl = findViewById(R.id.refresh);
        srl.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        try{
                            getView();
                        }catch(Exception e){

                        }
                        srl.setRefreshing(false);
                    }
                }, 5000);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                srl.setRefreshing(true);
                getView();
                srl.setRefreshing(false);
            }
        }, 5000);
    }

    public void getView() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String url = CommonVariable.DEFAULT_SOURCE;
        if (source.get(Storage.SOURCE__ID) != null) {
            url = source.get(Storage.SOURCE__ID);
        }

        Log.e("Source From", url);

        NewsListApi newsApi = new NewsListApi();
        List<NewsModel> news_list = newsApi.getNewsList("/everything", "&q=" + URLEncoder.encode(url) +"&from=" + dateFormat.format(date) + "&sortBy=publishedAt");

        ImageLoaderConfiguration ilc = new ImageLoaderConfiguration.Builder(NewsList.this).build();
        ImageLoader.getInstance().init(ilc);

        if(news_list != null){
            NewsAdapter news_adapter = new NewsAdapter(NewsList.this, R.layout.interface_news_list, news_list);
            list_view.setAdapter(news_adapter);

            list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    NewsModel news = (NewsModel) list_view.getAdapter().getItem(position);
                    storage.setNews(news);
                    startActivity(new Intent(NewsList.this, NewsDetail.class));
                }
            });

            list_view.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    return true;
                }
            });
        }
    }
}
