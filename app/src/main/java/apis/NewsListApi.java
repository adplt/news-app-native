package apis;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import model.NewsModel;
import utils.HTTP;
import variables.CommonVariable;

public class NewsListApi extends HTTP {

    private static String URL = CommonVariable.URL + CommonVariable.STATIC_PATH;
    private static String query = "?apiKey=" + CommonVariable.AUTH_KEY;

    public NewsListApi(){

    }

    public List<NewsModel> getNewsList(String params, String additionalQuery){
        List<NewsModel> news_list = new ArrayList<>();
        NewsModel news;

        //Ambil data dari JSON Object
        try{
            String response = get(URL, params, query + additionalQuery).toString();
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("articles");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    news = new NewsModel();

                    news.setID(i);
                    news.setAuthor(field.getString("author"));
                    news.setTitle(field.getString("title"));
                    news.setDescription(field.getString("description"));
                    news.setUrl(field.getString("url"));
                    news.setUrlToImage(field.getString("urlToImage"));
                    news.setPublishedAt(field.getString("publishedAt"));
                    news.setContent(field.getString("content"));

                    news_list.add(news);
                }else{
                    news_list = null;
                }
            }
        }catch(Exception e){
            //JSONException
            news_list = null;
        }

        return news_list;
    }
}
