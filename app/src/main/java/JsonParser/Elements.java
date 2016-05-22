package JsonParser;

import com.utm.miragessee.universiteticaretmerkezi.AnaActivity;

import org.json.*;
import java.util.ArrayList;

import Functions.*;

/**
 * Created by Orion on 20.11.2015.
 */
public class Elements /*implements IRestfulTask*/
{
    public ArrayList<ElementManager> elementsList = new ArrayList<ElementManager>();
    public  Elements(int index)
    {
        //getList(index);
        /*switch (index)
        {
            case 1:
                elementsList.add(new ElementManager("resim","Elektronik","antalya","100"));
                elementsList.add(new ElementManager("resim","Elektronik2","antalya","200"));
                break;
            case 2:
                elementsList.add(new ElementManager("resim","Kirtasiye","antalya","300"));
                elementsList.add(new ElementManager("resim","Kirtasiye2","antalya","400"));
                break;
            default:
                elementsList.add(new ElementManager("resim","test","antalya","350"));
                elementsList.add(new ElementManager("resim","test2","antalya","450"));
                elementsList.add(new ElementManager("resim","test3","antalya","550"));
                elementsList.add(new ElementManager("resim","test4","antalya","650"));
                break;
        }*/

    }
    public ArrayList<ElementManager> getElementsList() {
        return elementsList;
    }
    public void setElementsList(ArrayList<ElementManager> list)
    {
        this.elementsList = list;
    }
    //public ArrayList<ElementManager> getList(int i)
    public JSONObject getList(int i)
    {
        JSONObject params = null, func = null;
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token",AnaActivity.getLogin_token());
            params.put("category_id",i);
            func = new JSONObject();
            func.put("method_name", "getCategoryList");
            func.put("method_params", params);
        } catch (JSONException ex) {

        }
        return func;
        //RestFul restful = new RestFul();
        //Basic b = new Basic();
        //String JSONResponse = restful.JSONRequest(func);
        //GetCategoryList catList = new GetCategoryList(JSONResponse);
        //return catList.list();
        /*RestfulTask2 task = new RestfulTask2(new IRestfulTask() {
            @Override
            public void postResult(String s) {
                System.out.println("JSON GELEN : " + s);
                GetCategoryList catList = new GetCategoryList(s);
                elementsList = catList.list();
            }
        });
        task.execute(func);*/
    }

   /* @Override
    public void postResult(String s) {
        //GetCategoryList catList = new GetCategoryList(s);
        //elementsList = catList.list();
    }*/
}
