package utils;
import android.os.StrictMode;
import android.util.Log;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

public class HTTP {

    public HTTP(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public StringBuffer get(String path, String params, String query){
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String line = "";

        try{
            URL url = new URL(path + params + query);
            HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
            connect.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            connect.addRequestProperty("User-Agent", "Mozilla");

            StringBuilder builder = new StringBuilder();
            builder.append(connect.getResponseCode())
                    .append(" ")
                    .append(connect.getResponseMessage())
                    .append(" ")
                    .append(connect.getURL())
                    .append("\n");

            Map<String, List<String>> map = connect.getHeaderFields();
            for (Map.Entry<String, List<String>> entry : map.entrySet())
            {
                if (entry.getKey() == null)
                    continue;
                builder.append( entry.getKey())
                        .append(": ");

                List<String> headerValues = entry.getValue();
                Iterator<String> it = headerValues.iterator();
                if (it.hasNext()) {
                    builder.append(it.next());

                    while (it.hasNext()) {
                        builder.append(", ")
                                .append(it.next());
                    }
                }

                builder.append("\n");
            }

            Log.e("Punya Atria: ", String.valueOf(builder));

            if(connect.getInputStream() != null) {
                //mengambil response
                InputStream is = connect.getInputStream();
                //buat nampung buffer dari response.
                br = new BufferedReader(new InputStreamReader(is));
                //buat menampung String dari BufferedReader yang udah dibagi2.

                //dari BufferedReader kan String'a dalam bentuk 1 line doang, nah ini mw di potong2 string'a.
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        }catch(Exception e){
            //MalformedURLException, IOException
            sb = null;
        }finally{
            try{
                br.close();
            }catch(Exception e){
                //IOException
                sb=null;
                Log.e("ExecuteURL",e.getMessage());
            }
        }

        return sb;
    }

    public StringBuffer post(String path, JSONObject body, String params, String query){
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String line = "";

        try{
            URL url = new URL(path + params + query);
            HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
            connect.setDoOutput(true);
            connect.setRequestMethod("POST");
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connect.setRequestProperty("charset", "utf-8");
            DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
            wr.writeBytes(String.valueOf(body));
            wr.flush();
            wr.close();

            if(connect.getInputStream() != null) {
                //mengambil response
                InputStream is = connect.getInputStream();
                //buat nampung buffer dari response.
                br = new BufferedReader(new InputStreamReader(is));
                //buat menampung String dari BufferedReader yang udah dibagi2.

                //dari BufferedReader kan String'a dalam bentuk 1 line doang, nah ini mw di potong2 string'a.
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        }catch(Exception e){
            //MalformedURLException, IOException
            sb = null;
        }finally{
            try{
                br.close();
            }catch(Exception e){
                //IOException
                sb=null;
                Log.e("ExecuteURL",e.getMessage());
            }
        }

        return sb;
    }

    public StringBuffer put(String path, JSONObject body, String params, String query){
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String line = "";

        try{
            URL url = new URL(path + params + query);
            HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
            connect.setDoOutput(true);
            connect.setRequestMethod("PUT");
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connect.setRequestProperty("charset", "utf-8");
            DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
            wr.writeBytes(String.valueOf(body));
            wr.flush();
            wr.close();

            if(connect.getInputStream() != null) {
                //mengambil response
                InputStream is = connect.getInputStream();
                //buat nampung buffer dari response.
                br = new BufferedReader(new InputStreamReader(is));
                //buat menampung String dari BufferedReader yang udah dibagi2.

                //dari BufferedReader kan String'a dalam bentuk 1 line doang, nah ini mw di potong2 string'a.
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        }catch(Exception e){
            //MalformedURLException, IOException
            sb = null;
        }finally{
            try{
                br.close();
            }catch(Exception e){
                //IOException
                sb=null;
                Log.e("ExecuteURL",e.getMessage());
            }
        }

        return sb;
    }

    public StringBuffer delete(String path, JSONObject body, String params, String query){
        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        String line = "";

        try{
            URL url = new URL(path + params + query);
            HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
            connect.setDoOutput(true);
            connect.setRequestMethod("DELETE");
            connect.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connect.setRequestProperty("charset", "utf-8");
            DataOutputStream wr = new DataOutputStream(connect.getOutputStream());
            wr.writeBytes(String.valueOf(body));
            wr.flush();
            wr.close();

            if(connect.getInputStream() != null) {
                //mengambil response
                InputStream is = connect.getInputStream();
                //buat nampung buffer dari response.
                br = new BufferedReader(new InputStreamReader(is));
                //buat menampung String dari BufferedReader yang udah dibagi2.

                //dari BufferedReader kan String'a dalam bentuk 1 line doang, nah ini mw di potong2 string'a.
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
            }
        }catch(Exception e){
            //MalformedURLException, IOException
            sb = null;
        }finally{
            try{
                br.close();
            }catch(Exception e){
                //IOException
                sb=null;
                Log.e("ExecuteURL",e.getMessage());
            }
        }

        return sb;
    }
}
