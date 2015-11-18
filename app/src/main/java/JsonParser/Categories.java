package JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import Functions.Basic;

/**
 * Created by Orion on 15.11.2015.
 */
public class Categories
{
    public ArrayList<CategoryManager> categoriesList = new ArrayList<CategoryManager>();
    public  Categories()
    {
        Basic basic = new Basic();
        HashMap<Integer, CategoryManager> categoryMap = basic.getCategoryMap();
        /*for (HashMap.Entry<Integer, CategoryManager> entry : categoryMap.entrySet()) {
            categoriesList.add(entry.getValue());
        }*/
        for(int i = 1;i<=categoryMap.size();i++)
        {
            categoriesList.add(categoryMap.get(i));
        }

    }
    public ArrayList<CategoryManager> getCategoriesList() {
        return categoriesList;
    }
}

/*
 try {
                JSONObject jsonResponse = new JSONObject(JSONResponse);
                JSONArray jsonArray = jsonResponse.getJSONArray("getAllCategories");
                int arrayLength = jsonArray.length();
                if(arrayLength > 0)
                {
                    for(int i = 0;i<arrayLength;i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        CategoryManager manager = new CategoryManager(obj.getInt("id"),obj.getString("name"),obj.getInt("parent_id"));
                        categoriesList.add(manager);
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }
 */