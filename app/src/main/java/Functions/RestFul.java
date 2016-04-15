package Functions;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.utm.miragessee.universiteticaretmerkezi.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Object;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.json.*;
//import org.apache.http.client.*;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.*;
/**
 * Created by Orion on 10.11.2015.
 */
public class RestFul
{
    public static String restfulURL = "";

    public String JSONRequest(JSONObject json) {
            HttpClient httpClient = new DefaultHttpClient();
            try {
                HttpPost request = new HttpPost(restfulURL);
                System.out.println("JSON DATA : " + json.toString());
                StringEntity entity = new StringEntity(json.toString());
                request.addHeader("content-type", "application/x-www-form-urlencoded");
                request.setEntity(entity);
                HttpResponse response = httpClient.execute(request);
                System.out.println("Status Code : " + response.getStatusLine());
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = rd.readLine()) != null) {
                    sb.append(line + NL);
                }
                rd.close();
                return sb.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        return "";
    }
}

/*
JSONObject jsonResponse = new JSONObject(sb.toString());
        //System.out.println(jsonResponse.get("method_name"));
        JSONArray jsonArray = jsonResponse.getJSONArray("uyeGetir");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            Log.d("ID :", obj.getString("id"));
        }
 */