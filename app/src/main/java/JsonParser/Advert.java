package JsonParser;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;

/**
 * Created by Orion on 26.05.2016.
 */
public class Advert implements Serializable
{
    private int result = -1;
    private int advertId;
    private int userId;
    private int categoryId;
    private String title;
    private int price;
    private int cityPosition;
    private int universityPosition;
    private String details;
    private String date;
    private String firstname;
    private String lastname;
    private String phone;

    private ArrayList<String> photoList = null;
    public Advert(String json)
    {
        try {
            JSONObject jsonResponse = new JSONObject(json);
            if(jsonResponse.has("getAdvert"))
            {
                JSONObject advert = jsonResponse.getJSONObject("getAdvert");
                result = advert.getInt("result");
                Log.d("RESULT : ",String.valueOf(result));
                if(result == 1)
                {
                    JSONArray advertArray = advert.getJSONArray("advert");
                    Log.d("ADVERT JSON : ",advertArray.toString());
                    JSONObject obj = advertArray.getJSONObject(0);
                    advertId = obj.getInt("advert_id");
                    Log.d("ADVERTID : " ,String.valueOf(advertId));
                    userId = obj.getInt("user_id");
                    categoryId = obj.getInt("category_id");
                    title = obj.getString("title");
                    details = obj.getString("details");
                    price = obj.getInt("price");
                    cityPosition = obj.getInt("cityPosition");
                    universityPosition = obj.getInt("universityPosition");
                    date = obj.getString("create_date");
                    firstname = obj.getString("firstname");
                    lastname = obj.getString("lastname");
                    phone = obj.getString("phone");
                    JSONArray photo = advert.getJSONArray("photos");
                    photoList = new ArrayList<String>();
                    for(int i =0;i<photo.length();i++)
                    {
                        JSONObject p = photo.getJSONObject(i);
                        photoList.add(p.getString("photodata"));
                        Log.d("PHOTO : ",p.getString("photodata"));
                    }
                }
            }
        }catch (JSONException ex)
        {
            Log.d("ADVERT EXCEP : " ,ex.getMessage());
            ex.printStackTrace();
        }
    }
    public ArrayList<String> getPhotoList() {
        return photoList;
    }

    public String getPhone() {
        return phone;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDate() {
        return date;
    }

    public String getDetails() {
        return details;
    }

    public int getUniversityPosition() {
        return universityPosition;
    }

    public int getCityPosition() {
        return cityPosition;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public int getUserId() {
        return userId;
    }

    public int getAdvertId() {
        return advertId;
    }

}
