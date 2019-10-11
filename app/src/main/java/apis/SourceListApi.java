package apis;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import model.SourceModel;
import utils.HTTP;
import variables.CommonVariable;

public class SourceListApi extends HTTP {
    private static String URL = CommonVariable.URL + CommonVariable.STATIC_PATH;
    private static String query = "?apiKey=" + CommonVariable.AUTH_KEY;

    public SourceListApi(){

    }

    public List<SourceModel> getSourceList(String params, String additionalQuery){
        List<SourceModel> source_list = new ArrayList<>();
        SourceModel source;

        //Ambil data dari JSON Object
        try{
            String response = get(URL, params, query + additionalQuery).toString();
            JSONObject object = new JSONObject(response);
            JSONArray array = object.getJSONArray("sources");

            for(int i=0;i<array.length();i++){
                if(array.getJSONObject(i)!=null){
                    JSONObject field = array.getJSONObject(i);

                    source = new SourceModel();
                    source.setID(field.getString("id"));
                    source.setName(field.getString("name"));
                    source.setDescription(field.getString("description"));
                    source.setUrl(field.getString("url"));
                    source.setCategory(field.getString("category"));
                    source.setLanguage(field.getString("language"));
                    source.setCountry(field.getString("country"));

                    source_list.add(source);
                }else{
                    source_list = null;
                }
            }
        }catch(Exception e){
            //JSONException
            source_list = null;
        }

        return source_list;
    }
}
