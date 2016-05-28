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
    private int sender_id;
    private String title;
    private String sender;
    private String date;
    private String message;
    private int read;
    private String phone;

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
                list = new ArrayList<PMessage>();
                if(result == 1) {
                    JSONArray message = msgArray.getJSONArray("messages");
                    for (int i = 0; i < message.length(); i++) {
                        JSONObject m = message.getJSONObject(i);
                        Log.d("BOOL READ : ",String.valueOf(m.getInt("isread")));
                        PMessage pm = new PMessage(m.getInt("id"),m.getInt("advert_id"),m.getInt("backup_sender"),m.getString("title"),m.getString("firstname") + " " +m.getString("lastname"),m.getString("date"),m.getString("message"),m.getInt("isread"),m.getString("phone"));
                        list.add(pm);
                    }
                }
            }
        }catch (Exception ex)
        {
            Log.d("PMessage : ",ex.getMessage());
        }
    }
    public PMessage(int msgid, int advert_id, int sender_id, String title, String sender, String date, String message, int read, String phone) {
        this.msgid = msgid;
        this.advert_id = advert_id;
        this.sender_id = sender_id;
        this.title = title;
        this.sender = sender;
        this.date = date;
        this.message = message;
        this.read = read;
        this.phone = phone;
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

    public int getSender_id() {
        return sender_id;
    }

    public String getPhone() {
        return phone;
    }
}
