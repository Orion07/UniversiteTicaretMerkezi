package JsonParser;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Orion on 10.11.2015.
 */
public class Login
{
    private int login;
    private String email;
    private String login_token;
    private int result;

    public Login(String jsonStr)
    {
        try {
            JSONObject json = new JSONObject(jsonStr);
            if(json.has("login")) {
                JSONObject jsonLogin = (JSONObject) json.get("login");
                result = jsonLogin.getInt("result");
                if(result == 1) {
                    login = jsonLogin.getInt("is_login");
                    email = jsonLogin.getString("email");
                    login_token = jsonLogin.getString("login_token");
                }

            }else
            {
                Log.i("JSON LOGIN : ","Login basarisiz oldu.");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int islogin() { return login; }

    public String getEmail() { return email; }

    public String getLogin_token() {
        return login_token;
    }

    public int getResult() { return result; }

    public String getMessage()
    {
        switch (result)
        {
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
