package utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Middleware {
    public String newsList(JSONObject payload) throws JSONException {
        try {
            String q = payload.getString("q");
            String from = payload.getString("from");
            String sortBy = payload.getString("sortBy");
            return "&q=" + q + "&from=" + from + "&sortBy=" + sortBy;
        } catch (Exception e){
            throw e;
        }
    }
}
