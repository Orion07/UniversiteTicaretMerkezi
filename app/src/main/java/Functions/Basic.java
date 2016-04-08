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
    public static String[] cities = {"","Adana", "Adıyaman", "Afyon", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa", "Çanakkale",
            "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan", "Erzurum", "Eskişehir",
            "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta", "Mersin", "İstanbul", "İzmir",
            "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir", "Kocaeli", "Konya", "Kütahya", "Malatya",
            "Manisa", "Kahramanmaraş", "Mardin", "Muğla", "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya",
            "Samsun", "Siirt", "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman", "Şırnak",
            "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"};
    public static String[] universities = {"Abant İzzet Baysal Üniversitesi","Acıbadem Üniversitesi","Adıyaman Üniversitesi","Adnan Menderes Üniversitesi","Afyon Kocatepe Üniversitesi",
            "Ağrı İbrahim Çeçen Üniversitesi","Ahi Evran Üniversitesi","Akaki Tsereteli Devlet Üniversitesi","Akdeniz Üniversitesi","Aksaray Üniversitesi","Amasya Üniversitesi",
            "Anadolu Üniversitesi","Ankara Üniversitesi","Ardahan Üniversitesi","Artvin Çoruh Üniversitesi","Atatürk Üniversitesi","Atılım Üniversitesi","Avrasya Üniversitesi",
            "Azerbaycan Devlet Diller Üniversitesi","Azerbaycan Devlet İktisat Üniversitesi","Azerbaycan Devlet Kültür Ve Güzel Sanatlar Üniversitesi","Azerbaycan Devlet Neft Akademisi",
            "Azerbaycan Devlet Pedagoji Üniversitesi","Azerbaycan Mimarlık Ve İnşaat Üniversitesi","Azerbaycan Teknik Üniversitesi","Azerbaycan Tıp Üniversitesi","Bahçeşehir Üniversitesi",
            "Bakü Devlet Üniversitesi","Bakü Slavyan Üniversitesi","Balıkesir Üniversitesi","Bartın Üniversitesi","Başkent Üniversitesi","Batman Üniversitesi",
            "Batum Şota Rustaveli Devlet Üniversitesi","Bayburt Üniversitesi","Beykent Üniversitesi","Bezm-i Âlem Vakıf Üniversitesi","Bilecik Üniversitesi","Bilkent Üniversitesi",
            "Bingöl Üniversitesi","Bitlis Eren Üniversitesi","Boğaziçi Üniversitesi","Bozok Üniversitesi","Bursa Teknik Üniversitesi","Celal Bayar Üniversitesi","Cumhuriyet Üniversitesi",
            "Çağ Üniversitesi","Çanakkale Onsekiz Mart Üniversitesi","Çankaya Üniversitesi","Çankırı Karatekin Üniversitesi","Çukurova Üniversitesi","Dicle Üniversitesi",
            "Doğu Akdeniz Üniversitesi","Doğuş Üniversitesi","Dokuz Eylül Üniversitesi","Dumlupınar Üniversitesi","Düzce Üniversitesi","Ege Üniversitesi","Erciyes Üniversitesi",
            "Erzincan Üniversitesi","Eskişehir Osmangazi Üniversitesi","Fatih Sultan Mehmet Vakıf Üniversitesi","Fatih Üniversitesi","Fırat Üniversitesi","Galatasaray Üniversitesi",
            "Gazi Üniversitesi","Gaziantep Üniversitesi","Gazikent Üniversitesi","Gaziosmanpaşa Üniversitesi","Gebze Yüksek Teknoloji Enstitüsü","Gedik Üniversitesi","Gediz Üniversitesi",
            "Gence Devlet Üniversitesi","Giresun Üniversitesi","Girne Amerikan Üniversitesi","Gülhane Askeri Tıp Akademisi","Gümüşhane Üniversitesi","Hacettepe Üniversitesi",
            "Hakkari Üniversitesi","Haliç Üniversitesi","Harran Üniversitesi","Hitit Üniversitesi","Hoca Ahmet Yesevi Uluslararası Türk-kazak Üniversitesi","Iğdır Üniversitesi",
            "Işık Üniversitesi","İktisat Ve Girişimcilik Üniversitesi","İnönü Üniversitesi","İstanbul 29 Mayıs Üniversitesi","İstanbul Arel Üniversitesi","İstanbul Aydın Üniversitesi",
            "İstanbul Bilgi Üniversitesi","İstanbul Bilim Üniversitesi","İstanbul Gelişim Üniversitesi","İstanbul Kemerburgaz Üniversitesi","İstanbul Kültür Üniversitesi",
            "İstanbul Medipol Üniversitesi","İstanbul Sabahattin Zaim Üniversitesi","İstanbul Şehir Üniversitesi","İstanbul Teknik Üniversitesi","İstanbul Ticaret Üniversitesi",
            "İstanbul Üniversitesi","İzmir Ekonomi Üniversitesi","İzmir Katip Çelebi Üniversitesi","İzmir Üniversitesi","İzmir Yüksek Teknoloji Enstitüsü","Kadir Has Üniversitesi",
            "Kafkas Üniversitesi","Kahramanmaraş Sütçü İmam Üniversitesi","Karabük Üniversitesi","Karadeniz Teknik Üniversitesi","Karamanoğlu Mehmetbey Üniversitesi",
            "Kastamonu Üniversitesi","Kırgızistan-türkiye Manas Üniversitesi","Kırıkkale Üniversitesi","Kırklareli Üniversitesi","Kilis 7 Aralık Üniversitesi","Kocaeli Üniversitesi",
            "Koç Üniversitesi","Korkut Ata Kızılorda Devlet Üniversitesi","Kto Karatay Üniversitesi","Lefke Avrupa Üniversitesi","Maltepe Üniversitesi","Mardin Artuklu Üniversitesi",
            "Marmara Üniversitesi","Mehmet Akif Ersoy Üniversitesi","Melikşah Üniversitesi","Mersin Üniversitesi","Mevlana Üniversitesi","Mimar Sinan Güzel Sanatlar Üniversitesi",
            "Muğla Üniversitesi","Mustafa Kemal Üniversitesi","Muş Alparslan Üniversitesi","Namık Kemal Üniversitesi","Nevşehir Üniversitesi","Niğde Üniversitesi",
            "Nuh Naci Yazgan Üniversitesi","Okan Üniversitesi","Ondokuz Mayıs Üniversitesi","Ordu Üniversitesi","Orta Doğu Teknik Üniversitesi","Osmaniye Korkut Ata Üniversitesi",
            "Özyeğin Üniversitesi","Pamukkale Üniversitesi","Piri Reis Üniversitesi","Rize Üniversitesi","Sabancı Üniversitesi","Sakarya Üniversitesi","Selçuk Üniversitesi",
            "Siirt Üniversitesi","Sinop Üniversitesi","Sumgayıt Devlet Üniversitesi","Süleyman Demirel Üniversitesi","Süleyman Şah Üniversitesi","Şırnak Üniversitesi","Şifa Üniversitesi",
            "Tobb Ekonomi Ve Teknoloji Üniversitesi","Toros Üniversitesi","Trakya Üniversitesi","Tunceli Üniversitesi","Turgut Özal Üniversitesi","Türk Hava Kurumu Üniversitesi",
            "Ufuk Üniversitesi","Ukrayna Ulusal Teknik Üniversitesi","Uludağ Üniversitesi","Uluslararası Saraybosna Üniversitesi","Uluslararasi Kıbrıs Üniversitesi",
            "Uşak Üniversitesi","Yakın Doğu Üniversitesi","Yalova Üniversitesi","Yaşar Üniversitesi","Yeditepe Üniversitesi","Yeni Yüzyıl Üniversitesi","Yıldırım Beyazıt Üniversitesi",
            "Yıldız Teknik Üniversitesi","Yüzüncü Yıl Üniversitesi","Zirve Üniversitesi","Zonguldak Karaelmas Üniversitesi"};
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

