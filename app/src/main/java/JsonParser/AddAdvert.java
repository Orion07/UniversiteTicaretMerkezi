package JsonParser;

import com.utm.miragessee.universiteticaretmerkezi.AnaActivity;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orion.
 */
public class AddAdvert
{

    private String email;
    private String login_token;
    private int result = -1;

    public String getEmail() {
        return email;
    }

    public String getLogin_token() {
        return login_token;
    }

    public int getResult() {
        return result;
    }


    public AddAdvert(String jsonStr)
    {
        try {
            JSONObject json = new JSONObject(jsonStr);
            if(json.has("addAdvert"))
            {
                email = json.getString("email");
                login_token = json.getString("login_token");
                if(login_token.equals(AnaActivity.getLogin_token()) && email.equals(AnaActivity.getEmail()))
                {
                    result = json.getInt("result");
                }
            }
        }catch (JSONException e)
        {
            e.printStackTrace();
        }
        
    }
    public String getMessage()
    {
        //php kısmı yazılınca güncellencek
        switch (result)
        {
            case -1:
                return "Internet baglantisi yok";
            case 0:
                return "Hatali bilgi";
            case 1:
                return "Giris islemi basariyla gerceklestirildi";
            case 2:
                return "Sorgu Hatasi";
            case 3:
                return "Hesap aktif edilmemis.";
            case 4:
                return "Sifreniz yanlis";
            case 5:
                return "Boyle bir hesap yok";

        }
        return "Bilinmeyen Hata[LOGIN]";
    }
}