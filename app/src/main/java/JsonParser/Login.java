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
                Log.i("JSON LOGIN : ","qnq json yoq");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public int islogin() { return login; }

    public String getEmail() {
        return email;
    }

    public String getLogin_token() {
        return login_token;
    }

    public int getResult() { return result; }
}
