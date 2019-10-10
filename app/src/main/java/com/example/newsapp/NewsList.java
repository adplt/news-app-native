package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.android.material.navigation.NavigationView;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import java.util.List;
import adapter.NewsAdapter;
import apis.NewsListApi;
import model.NewsModel;
import utils.Middleware;
import utils.Storage;

public class NewsList extends AppCompatActivity {

    private ListView list_view;
    private Storage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);
        list_view = findViewById(R.id.news_list);

        storage = new Storage(NewsList.this);
        getView();
    }

    public void getView() {
        NewsListApi newsApi = new NewsListApi();
        List<NewsModel> news_list = newsApi.getNewsList("/everything", "&q=bitcoin&from=2019-09-10&sortBy=publishedAt");

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
