package JsonParser;

import android.renderscript.Element;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import Functions.Basic;

/**
 * Created by Orion on 22.05.2016.
 */
public class GetCategoryList
{
    public ArrayList<ElementManager> elementsList = new ArrayList<ElementManager>();
    public GetCategoryList(String jsonStr)
    {
        try {
            JSONObject json = new JSONObject(jsonStr);
            if(json.has("getCategoryList")) {
                JSONObject jsonCategory = (JSONObject) json.get("getCategoryList");
                int result = jsonCategory.getInt("result");
                if(result == 1) {
                    JSONArray jsonArray = jsonCategory.getJSONArray("list");
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        int advertid = obj.getInt("advert_id");
                        String photo = obj.getString("photodata");
                        String title = obj.getString("title");
                        String city = Basic.cities[obj.getInt("cityPosition")];
                        String price = String.valueOf(obj.getInt("price"));
                        ElementManager element = new ElementManager(advertid,photo,title,city,price);
                        elementsList.add(element);
                    }
                }

            }else
            {
                Log.i("JSON CATEGORY : ", "Kategorileri çekerken hata oluştu");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<ElementManager> list()
    {
        return elementsList;
    }
}
