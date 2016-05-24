package JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orion on 24.05.2016.
 */
public class ChangePassword
{
    private int result = -1;
    public ChangePassword(String json)
    {
        try {
            JSONObject obj = new JSONObject(json);
            if(obj.has("changePassword"))
            {
                JSONObject pw = obj.getJSONObject("changePassword");
                result = pw.getInt("result");
                int row = pw.getInt("rowCount");
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
                return "Şifre değiştirme işlemi başarılı";
            case 2:
                return "Sorgu Hatasi";
            case 3:
                return "Girmiş olduğunuz şifreniz yanlış";
            case 4:
                return "Sorgu Hatas #2";
            case 5:
                return "Login Token geçersiz";

        }
        return "Bilinmeyen Hata[PASSWORD]: " + result ;
    }
}
