package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import utils.Storage;

public class NewsDetail extends AppCompatActivity {

    private WebView wv;
    private Storage storage;
    private HashMap<String,String> news;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        wv = findViewById(R.id.news_detail);
        TextView close_detail = findViewById(R.id.close_web_view);

        storage = new Storage(NewsDetail.this);
        news = storage.getNews();

        wv.loadUrl(news.get(Storage.NEWS__URL));
        close_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
