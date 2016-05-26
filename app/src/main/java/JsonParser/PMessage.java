package JsonParser;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by Orion on 25.05.2016.
 */
public class PMessage implements Serializable
{
    private int msgid;
    private String title;
    private String sender;
    private Date date;
    private String message;
    private boolean read;
    public PMessage(int msgid,String title,String sender,Date date,String message,boolean read)
    {
        this.msgid = msgid;
        this.title = title;
        this.sender = sender;
        this.date = date;
        this.message = message;
        this.read = read;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}
