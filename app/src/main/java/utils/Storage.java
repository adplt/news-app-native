package utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

import model.NewsModel;

public class Storage {
    private SharedPreferences sp;
    private SharedPreferences.Editor e;
    private Context c;

    public Storage(Context c){
        this.c=c;
    }

    // News
    public static final String NEWS__ = "NEWS__";
    public static final String NEWS__ID = "NEWS__ID";
    public static final String NEWS__SOURCE_ID = "NEWS__SOURCE_ID";
    public static final String NEWS__SOURCE_NAME = "NEWS__SOURCE_NAME";
    public static final String NEWS__AUTHOR = "NEWS__AUTHOR";
    public static final String NEWS__TITLE = "NEWS__TITLE";
    public static final String NEWS__DESCRIPTION = "NEWS__DESCRIPTION";
    public static final String NEWS__URL = "NEWS__URL";
    public static final String NEWS__URL_TO_IMAGE = "NEWS__URL_TO_IMAGE";
    public static final String NEWS__PUBLISHED_AT = "NEWS__PUBLISHED_AT";
    public static final String NEWS__CONTENT = "NEWS__CONTENT";

    public void setNews(NewsModel news){
        sp = c.getSharedPreferences(NEWS__, Context.MODE_PRIVATE); //0 means private
        e = sp.edit();

        e.putInt(NEWS__ID, news.getID());
        e.putString(NEWS__SOURCE_ID, news.getSource().getID());
        e.putString(NEWS__SOURCE_NAME, news.getSource().getID());
        e.putString(NEWS__AUTHOR, news.getAuthor());
        e.putString(NEWS__TITLE, news.getTitle());
        e.putString(NEWS__DESCRIPTION, news.getDescription());
        e.putString(NEWS__URL, news.getUrl());
        e.putString(NEWS__URL_TO_IMAGE, news.getUrlToImage());
        e.putString(NEWS__PUBLISHED_AT, news.getPublishedAt());
        e.putString(NEWS__CONTENT, news.getContent());

        e.commit();
    }

    public HashMap<String,String> getNews(){
        HashMap<String, String> data = new HashMap<>();
        sp = c.getSharedPreferences(NEWS__, Context.MODE_PRIVATE); //0 means private

        data.put(NEWS__ID, String.valueOf(sp.getInt(NEWS__ID, 0)));
        data.put(NEWS__SOURCE_ID, sp.getString(NEWS__SOURCE_ID, null));
        data.put(NEWS__SOURCE_NAME, sp.getString(NEWS__SOURCE_NAME, null));
        data.put(NEWS__AUTHOR, sp.getString(NEWS__AUTHOR, null));
        data.put(NEWS__TITLE,sp.getString(NEWS__TITLE, null));
        data.put(NEWS__DESCRIPTION, sp.getString(NEWS__DESCRIPTION, null));
        data.put(NEWS__URL, sp.getString(NEWS__URL, null));
        data.put(NEWS__URL_TO_IMAGE, sp.getString(NEWS__URL_TO_IMAGE, null));
        data.put(NEWS__PUBLISHED_AT, sp.getString(NEWS__PUBLISHED_AT, null));
        data.put(NEWS__CONTENT, sp.getString(NEWS__CONTENT, null));

        return data;
    }
}
