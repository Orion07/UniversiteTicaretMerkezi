package JsonParser;

import android.util.Log;

import com.utm.miragessee.universiteticaretmerkezi.PmActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Orion on 25.05.2016.
 */
public class PMessage implements Serializable
{
    private int msgid;
    private int advert_id;
    private String title;
    private String sender;
    private String date;
    private String message;
    private int read;
    private ArrayList<PMessage> list = null;
    //m.advert_id,a.title,u.firstname,u.lastname,m.id,m.message,m.date,m.isread
    public PMessage(String json)
    {
        System.out.println("PM JSON : " + json);
        JSONObject params = null,func = null;
        try {
            JSONObject obj = new JSONObject(json);
            if(obj.has("getMessage"))
            {
                JSONObject msgArray = obj.getJSONObject("getMessage");
                int result = msgArray.getInt("result");
                if(result == 1) {
                    JSONArray message = msgArray.getJSONArray("messages");
                    list = new ArrayList<PMessage>();
                    for (int i = 0; i < message.length(); i++) {
                        //m.advert_id,a.title,u.firstname,u.lastname,m.id,m.message,m.date,m.isread
                        JSONObject m = message.getJSONObject(i);
                        Log.d("BOOL READ : ",String.valueOf(m.getInt("isread")));
                        PMessage pm = new PMessage(m.getInt("id"),m.getInt("advert_id"),m.getString("title"),m.getString("firstname") + " " +m.getString("lastname"),m.getString("date"),m.getString("message"),(m.getInt("isread")));
                        list.add(pm);
                    }
                }
            }
        }catch (Exception ex)
        {
            Log.d("PMessage : ",ex.getMessage());
        }
    }

    public PMessage(int msgid, int advert_id, String title, String sender, String date, String message, int read) {
        this.msgid = msgid;
        this.advert_id = advert_id;
        this.title = title;
        this.sender = sender;
        this.date = date;
        this.message = message;
        this.read = read;
    }

    public ArrayList<PMessage> getList() {
        return list;
    }

    public int getMsgid() {
        return msgid;
    }

    public int getAdvert_id() {
        return advert_id;
    }

    public String getTitle() {
        return title;
    }

    public String getSender() {
        return sender;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }

    public int getRead() {
        return read;
    }
}
