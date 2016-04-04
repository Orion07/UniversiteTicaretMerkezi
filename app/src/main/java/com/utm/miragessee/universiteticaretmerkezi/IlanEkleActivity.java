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
