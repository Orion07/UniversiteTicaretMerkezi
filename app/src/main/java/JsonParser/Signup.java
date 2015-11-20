package JsonParser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orion on 11.11.2015.
 */
public class Signup {
    private int result = -1;
    public Signup(String jsonStr)
    {
        try{
            JSONObject json = new JSONObject(jsonStr);
            if(json.has("signup"))
            {
                JSONObject jsonSignup = (JSONObject)json.get("signup");
                result = jsonSignup.getInt("result");
            }

        }catch (JSONException ex)
        {
            Log.i("Signup JSON Error : ",ex.getMessage());
        }

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
                return "Kayit yapildi,Lutfen giris yapiniz";
            case 2:
                return "Bu bilgilere ait bir kayit mevcuttur ";
            case 3:
            case 4:
                return "Sorgu Hatasi";

        }
        return "Bilinmeyen Hata[SIGNUP]";
    }
    public int getResult() {
        return result;
    }


}
