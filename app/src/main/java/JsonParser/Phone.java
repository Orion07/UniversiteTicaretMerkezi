package JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orion on 25.05.2016.
 */
public class Phone
{
    private int result = -1;
    public Phone(String json)
    {
        try {
            JSONObject obj = new JSONObject(json);
            if(obj.has("changePhone"))
            {
                JSONObject pw = obj.getJSONObject("changePhone");
                result = pw.getInt("result");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public int getResult()
    {
        return result;
    }

    public void setResult(int result)
    {
        this.result = result;
    }
    public String getMessage()
    {
        switch (result)
        {
            case -1:
                return "Internet baglantisi yok";
            case 0:
                return "Hatali bilgi";
            case 1:
                return "Telefon değiştirme işlemi başarılı";
            case 2:
                return "Sorgu Hatasi";
            case 3:
                return "Login Token geçersiz";

        }
        return "Bilinmeyen Hata[PHONE]: " + result ;
    }
}
