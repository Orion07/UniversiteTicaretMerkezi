package Functions;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by Orion on 10.11.2015.
 */
public class Basic_Functions
{
    public boolean checkEmail(String email)
    {
        if(email.indexOf("@")>-1 && email.endsWith("edu.tr"))
            return true;
        else
            return false;
    }
    public void MsgBox(Context ctx,String msg)
    {
        Toast.makeText(ctx,msg,Toast.LENGTH_LONG).show();
    }
    public String getDeviceID(Context ctx)
    {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}

