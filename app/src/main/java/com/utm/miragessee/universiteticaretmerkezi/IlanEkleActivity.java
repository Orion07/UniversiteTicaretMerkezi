package com.utm.miragessee.universiteticaretmerkezi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class IlanEkleActivity extends AppCompatActivity {
    private String iller[];
    private String uniler[];
    ListView list;
    String[] web = {
            "İlan Başlığı",
            "Fiyat",
            "İl",
            "Üniversite",
            "Açıklama"
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_ekle);
        iller = new String[81];
        uniler = new String[177];
        iller[0]="Adana";
        iller[1]="Adıyaman";
        iller[2]="Afyonkarahisar";
        iller[3]="Ağrı";
        iller[4]="Amasya";
        iller[5]="Ankara";
        iller[6]="Antalya";
        iller[7]="Artvin";
        iller[8]="Aydın";
        iller[9]="Balıkesir";
        iller[10]="Bilecik";
        iller[11]="Bingöl";
        iller[12]="Bitlis";
        iller[13]="Bolu";
        iller[14]="Burdur";
        iller[15]="Bursa";
        iller[16]="Çanakkale";
        iller[17]="Çankırı";
        iller[18]="Çorum";
        iller[19]="Denizli";
        iller[20]="Diyarbakır";
        iller[21]="Edirne";
        iller[22]="Elazığ";
        iller[23]="Erzincan";
        iller[24]="Erzurum";
        iller[25]="Eskişehir";
        iller[26]="Gaziantep";
        iller[27]="Giresun";
        iller[28]="Gümüşhane";
        iller[29]="Hakkari";
        iller[30]="Hatay";
        iller[31]="Isparta";
        iller[32]="Mersin(İçel)";
        iller[33]="İstanbul";
        iller[34]="İzmir";
        iller[35]="Kars";
        iller[36]="Kastamonu";
        iller[37]="Kayseri";
        iller[38]="Kırklareli";
        iller[39]="Kırşehir";
        iller[40]="Kocaeli";
        iller[41]="Konya";
        iller[42]="Kütahya";
        iller[43]="Malatya";
        iller[44]="Manisa";
        iller[45]="Kahramanmaraş";
        iller[46]="Mardin";
        iller[47]="Muğla";
        iller[48]="Muş";
        iller[49]="Nevşehir";
        iller[50]="Niğde";
        iller[51]="Ordu";
        iller[52]="Rize";
        iller[53]="Sakarya";
        iller[54]="Samsun";
        iller[55]="Siirt";
        iller[56]="Sinop";
        iller[57]="Sivas";
        iller[58]="Tekirdağ";
        iller[59]="Tokat";
        iller[60]="Trabzon";
        iller[61]="Tunceli";
        iller[62]="Şanlıurfa";
        iller[63]="Uşak";
        iller[64]="Van";
        iller[65]="Yozgat";
        iller[66]="Zonguldak";
        iller[67]="Aksaray";
        iller[68]="Bayburt";
        iller[69]="Karaman";
        iller[70]="Kırıkkale";
        iller[71]="Batman";
        iller[72]="Şırnak";
        iller[73]="Bartın";
        iller[74]="Ardahan";
        iller[75]="Iğdır";
        iller[76]="Yalova";
        iller[77]="Karabük";
        iller[78]="Kilis";
        iller[79]="Osmaniye";
        iller[80]="Düzce";
        uniler[0]="Abant İzzet Baysal Üniversitesi";
        uniler[1]="Acıbadem Üniversitesi";
        uniler[2]="Adıyaman Üniversitesi";
        uniler[3]="Adnan Menderes Üniversitesi";
        uniler[4]="Afyon Kocatepe Üniversitesi";
        uniler[5]="Ağrı İbrahim Çeçen Üniversitesi";
        uniler[6]="Ahi Evran Üniversitesi";
        uniler[7]="Akaki Tsereteli Devlet Üniversitesi";
        uniler[8]="Akdeniz Üniversitesi";
        uniler[9]="Aksaray Üniversitesi";
        uniler[10]="Amasya Üniversitesi";
        uniler[11]="Anadolu Üniversitesi";
        uniler[12]="Ankara Üniversitesi";
        uniler[13]="Ardahan Üniversitesi";
        uniler[14]="Artvin Çoruh Üniversitesi";
        uniler[15]="Atatürk Üniversitesi";
        uniler[16]="Atılım Üniversitesi";
        uniler[17]="Avrasya Üniversitesi";
        uniler[18]="Azerbaycan Devlet Diller Üniversitesi";
        uniler[19]="Azerbaycan Devlet İktisat Üniversitesi";
        uniler[20]="Azerbaycan Devlet Kültür Ve Güzel Sanatlar Üniversitesi";
        uniler[21]="Azerbaycan Devlet Neft Akademisi";
        uniler[22]="Azerbaycan Devlet Pedagoji Üniversitesi";
        uniler[23]="Azerbaycan Mimarlık Ve İnşaat Üniversitesi";
        uniler[24]="Azerbaycan Teknik Üniversitesi";
        uniler[25]="Azerbaycan Tıp Üniversitesi";
        uniler[26]="Bahçeşehir Üniversitesi";
        uniler[27]="Bakü Devlet Üniversitesi";
        uniler[28]="Bakü Slavyan Üniversitesi";
        uniler[29]="Balıkesir Üniversitesi";
        uniler[30]="Bartın Üniversitesi";
        uniler[31]="Başkent Üniversitesi";
        uniler[32]="Batman Üniversitesi";
        uniler[33]="Batum Şota Rustaveli Devlet Üniversitesi";
        uniler[34]="Bayburt Üniversitesi";
        uniler[35]="Beykent Üniversitesi";
        uniler[36]="Bezm-i Âlem Vakıf Üniversitesi";
        uniler[37]="Bilecik Üniversitesi";
        uniler[38]="Bilkent Üniversitesi";
        uniler[39]="Bingöl Üniversitesi";
        uniler[40]="Bitlis Eren Üniversitesi";
        uniler[41]="Boğaziçi Üniversitesi";
        uniler[42]="Bozok Üniversitesi";
        uniler[43]="Bursa Teknik Üniversitesi";
        uniler[44]="Celal Bayar Üniversitesi";
        uniler[45]="Cumhuriyet Üniversitesi";
        uniler[46]="Çağ Üniversitesi";
        uniler[47]="Çanakkale Onsekiz Mart Üniversitesi";
        uniler[48]="Çankaya Üniversitesi";
        uniler[49]="Çankırı Karatekin Üniversitesi";
        uniler[50]="Çukurova Üniversitesi";
        uniler[51]="Dicle Üniversitesi";
        uniler[52]="Doğu Akdeniz Üniversitesi";
        uniler[53]="Doğuş Üniversitesi";
        uniler[54]="Dokuz Eylül Üniversitesi";
        uniler[55]="Dumlupınar Üniversitesi";
        uniler[56]="Düzce Üniversitesi";
        uniler[57]="Ege Üniversitesi";
        uniler[58]="Erciyes Üniversitesi";
        uniler[59]="Erzincan Üniversitesi";
        uniler[60]="Eskişehir Osmangazi Üniversitesi";
        uniler[61]="Fatih Sultan Mehmet Vakıf Üniversitesi";
        uniler[62]="Fatih Üniversitesi";
        uniler[63]="Fırat Üniversitesi";
        uniler[64]="Galatasaray Üniversitesi";
        uniler[65]="Gazi Üniversitesi";
        uniler[66]="Gaziantep Üniversitesi";
        uniler[67]="Gazikent Üniversitesi";
        uniler[68]="Gaziosmanpaşa Üniversitesi";
        uniler[69]="Gebze Yüksek Teknoloji Enstitüsü";
        uniler[70]="Gedik Üniversitesi";
        uniler[71]="Gediz Üniversitesi";
        uniler[72]="Gence Devlet Üniversitesi";
        uniler[73]="Giresun Üniversitesi";
        uniler[74]="Girne Amerikan Üniversitesi";
        uniler[75]="Gülhane Askeri Tıp Akademisi";
        uniler[76]="Gümüşhane Üniversitesi";
        uniler[77]="Hacettepe Üniversitesi";
        uniler[78]="Hakkari Üniversitesi";
        uniler[79]="Haliç Üniversitesi";
        uniler[80]="Harran Üniversitesi";
        uniler[81]="Hitit Üniversitesi";
        uniler[82]="Hoca Ahmet Yesevi Uluslararası Türk-kazak Üniversitesi";
        uniler[83]="Iğdır Üniversitesi";
        uniler[84]="Işık Üniversitesi";
        uniler[85]="İktisat Ve Girişimcilik Üniversitesi";
        uniler[86]="İnönü Üniversitesi";
        uniler[87]="İstanbul 29 Mayıs Üniversitesi";
        uniler[88]="İstanbul Arel Üniversitesi";
        uniler[89]="İstanbul Aydın Üniversitesi";
        uniler[90]="İstanbul Bilgi Üniversitesi";
        uniler[91]="İstanbul Bilim Üniversitesi";
        uniler[92]="İstanbul Gelişim Üniversitesi";
        uniler[93]="İstanbul Kemerburgaz Üniversitesi";
        uniler[94]="İstanbul Kültür Üniversitesi";
        uniler[95]="İstanbul Medipol Üniversitesi";
        uniler[96]="İstanbul Sabahattin Zaim Üniversitesi";
        uniler[97]="İstanbul Şehir Üniversitesi";
        uniler[98]="İstanbul Teknik Üniversitesi";
        uniler[99]="İstanbul Ticaret Üniversitesi";
        uniler[100]="İstanbul Üniversitesi";
        uniler[101]="İzmir Ekonomi Üniversitesi";
        uniler[102]="İzmir Katip Çelebi Üniversitesi";
        uniler[103]="İzmir Üniversitesi";
        uniler[104]="İzmir Yüksek Teknoloji Enstitüsü";
        uniler[105]="Kadir Has Üniversitesi";
        uniler[106]="Kafkas Üniversitesi";
        uniler[107]="Kahramanmaraş Sütçü İmam Üniversitesi";
        uniler[108]="Karabük Üniversitesi";
        uniler[109]="Karadeniz Teknik Üniversitesi";
        uniler[110]="Karamanoğlu Mehmetbey Üniversitesi";
        uniler[111]="Kastamonu Üniversitesi";
        uniler[112]="Kırgızistan-türkiye Manas Üniversitesi";
        uniler[113]="Kırıkkale Üniversitesi";
        uniler[114]="Kırklareli Üniversitesi";
        uniler[115]="Kilis 7 Aralık Üniversitesi";
        uniler[116]="Kocaeli Üniversitesi";
        uniler[117]="Koç Üniversitesi";
        uniler[118]="Korkut Ata Kızılorda Devlet Üniversitesi";
        uniler[119]="Kto Karatay Üniversitesi";
        uniler[120]="Lefke Avrupa Üniversitesi";
        uniler[121]="Maltepe Üniversitesi";
        uniler[122]="Mardin Artuklu Üniversitesi";
        uniler[123]="Marmara Üniversitesi";
        uniler[124]="Mehmet Akif Ersoy Üniversitesi";
        uniler[125]="Melikşah Üniversitesi";
        uniler[126]="Mersin Üniversitesi";
        uniler[127]="Mevlana Üniversitesi";
        uniler[128]="Mimar Sinan Güzel Sanatlar Üniversitesi";
        uniler[129]="Muğla Üniversitesi";
        uniler[130]="Mustafa Kemal Üniversitesi";
        uniler[131]="Muş Alparslan Üniversitesi";
        uniler[132]="Namık Kemal Üniversitesi";
        uniler[133]="Nevşehir Üniversitesi";
        uniler[134]="Niğde Üniversitesi";
        uniler[135]="Nuh Naci Yazgan Üniversitesi";
        uniler[136]="Okan Üniversitesi";
        uniler[137]="Ondokuz Mayıs Üniversitesi";
        uniler[138]="Ordu Üniversitesi";
        uniler[139]="Orta Doğu Teknik Üniversitesi";
        uniler[140]="Osmaniye Korkut Ata Üniversitesi";
        uniler[141]="Özyeğin Üniversitesi";
        uniler[142]="Pamukkale Üniversitesi";
        uniler[143]="Piri Reis Üniversitesi";
        uniler[144]="Rize Üniversitesi";
        uniler[145]="Sabancı Üniversitesi";
        uniler[146]="Sakarya Üniversitesi";
        uniler[147]="Selçuk Üniversitesi";
        uniler[148]="Siirt Üniversitesi";
        uniler[149]="Sinop Üniversitesi";
        uniler[150]="Sumgayıt Devlet Üniversitesi";
        uniler[151]="Süleyman Demirel Üniversitesi";
        uniler[152]="Süleyman Şah Üniversitesi";
        uniler[153]="Şırnak Üniversitesi";
        uniler[154]="Şifa Üniversitesi";
        uniler[155]="Tobb Ekonomi Ve Teknoloji Üniversitesi";
        uniler[156]="Toros Üniversitesi";
        uniler[157]="Trakya Üniversitesi";
        uniler[158]="Tunceli Üniversitesi";
        uniler[159]="Turgut Özal Üniversitesi";
        uniler[160]="Türk Hava Kurumu Üniversitesi";
        uniler[161]="Ufuk Üniversitesi";
        uniler[162]="Ukrayna Ulusal Teknik Üniversitesi";
        uniler[163]="Uludağ Üniversitesi";
        uniler[164]="Uluslararası Saraybosna Üniversitesi";
        uniler[165]="Uluslararasi Kıbrıs Üniversitesi";
        uniler[166]="Uşak Üniversitesi";
        uniler[167]="Yakın Doğu Üniversitesi";
        uniler[168]="Yalova Üniversitesi";
        uniler[169]="Yaşar Üniversitesi";
        uniler[170]="Yeditepe Üniversitesi";
        uniler[171]="Yeni Yüzyıl Üniversitesi";
        uniler[172]="Yıldırım Beyazıt Üniversitesi";
        uniler[173]="Yıldız Teknik Üniversitesi";
        uniler[174]="Yüzüncü Yıl Üniversitesi";
        uniler[175]="Zirve Üniversitesi";
        uniler[176]="Zonguldak Karaelmas Üniversitesi";
        CustomList adapter = new CustomList(IlanEkleActivity.this, web);
        list=(ListView)findViewById(R.id.listView3);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(IlanEkleActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                if (position == 0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("İlan Başlığı");
                    final EditText edittext = new EditText(getApplicationContext());
                    builder.setView(edittext);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String ilanBasligi = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }
                if (position == 1) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Fiyat");
                    final EditText edittext = new EditText(getApplicationContext());
                    builder.setView(edittext);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String fiyat = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }
                if (position == 2) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("İller");
                    builder.setSingleChoiceItems(iller, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
                if (position == 3) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Üniversiteler");
                    builder.setSingleChoiceItems(uniler, -1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
                if (position == 4) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Açıklama");
                    final EditText edittext = new EditText(getApplicationContext());
                    builder.setView(edittext);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            String aciklama = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ilan_ekle, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
