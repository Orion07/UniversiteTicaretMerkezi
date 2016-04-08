package JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orion.
 */
public class SearchAdvert
{
    public SearchAdvert(String jsonStr)
    {
        try {
            JSONObject json = new JSONObject(jsonStr);

        }catch (JSONException e)
        {
            e.printStackTrace();
        }

    }
}
