package com.example.newsapp;

import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import utils.Storage;

public class NewsDetail extends AppCompatActivity {

    private WebView wv;
    private Storage storage;
    private HashMap<String,String> news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        wv = findViewById(R.id.news_detail);

        storage = new Storage(NewsDetail.this);
        news = storage.getNews();

        Log.e("Punya Atria", news.get(Storage.NEWS__URL));

        wv.loadUrl(news.get(Storage.NEWS__URL));
    }
}
