package Functions;

import android.content.Context;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;
import com.utm.miragessee.universiteticaretmerkezi.R;

import java.util.HashMap;

import JsonParser.CategoryManager;

/**
 * Created by Orion on 10.11.2015.
 */
public class Basic
{
    String[] cities = {"","Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    String[] categories = {"Elektronik","Kirtasiye","Ev arkadasi","Ozel Ders Verenler","Is ilanlari","Spor,Diger"};
    CharSequence[] tabNames = {"Profil","Mesajlar"};
    public boolean checkEmail(String email)
    {
        if(email.indexOf("@")>-1 && email.endsWith("edu.tr"))
            return true;
        else
            return false;
    }
    public CharSequence getTabName(int index)
    {
        return tabNames[index];
    }
    public int getTabCount()
    {
        return tabNames.length;
    }
    public void MsgBox(Context ctx,String msg)
    {
        Toast.makeText(ctx,msg,Toast.LENGTH_LONG).show();
    }
    public String getDeviceID(Context ctx)
    {
        return Settings.Secure.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
    public HashMap<Integer, CategoryManager> getCategoryMap()
    {
        HashMap<Integer, CategoryManager> map = new HashMap<Integer, CategoryManager>();

        map.put(1,new CategoryManager(1,"Elektronik", R.drawable.pc));
        map.put(2,new CategoryManager(2,"Kirtasiye",R.drawable.book));//book
        map.put(3,new CategoryManager(3,"Ev Arkadasi",R.drawable.person));//person
        map.put(4,new CategoryManager(4,"Ozel Ders Verenler",R.drawable.study));//study
        map.put(5,new CategoryManager(5,"Is ilanlari", R.drawable.suitcase));//suitcase
        map.put(6,new CategoryManager(6,"Spor",R.drawable.sport));//sport
        map.put(7,new CategoryManager(7,"Diger",R.drawable.ic_action_name));//android
        return map;
    }
    public HashMap<Integer,Integer> getSubCategoryMap()
    {
        //coming soon xd
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        return map;
    }
}

