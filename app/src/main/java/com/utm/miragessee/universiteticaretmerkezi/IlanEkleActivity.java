package com.utm.miragessee.universiteticaretmerkezi;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import Functions.Basic;
import Functions.RestFul;
import JsonParser.AddAdvert;
import JsonParser.Signup;

public class IlanEkleActivity extends AppCompatActivity {

    ListView list;
    String title = null;
    int price = 0;
    int cityPosition = -1;
    int universityPosition = -1;
    String details = null;
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
        final JSONObject params = new JSONObject(), func = new JSONObject();
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
                    edittext.setText(title);
                    builder.setView(edittext);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            title = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }
                if (position == 1) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Fiyat");
                    final EditText edittext = new EditText(getApplicationContext());
                    edittext.setText(price);
                    builder.setView(edittext);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           price = Integer.parseInt(edittext.getText().toString());
                        }
                    });
                    alertDialog.show();
                }
                if (position == 2) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("İller");
                    builder.setSingleChoiceItems(Basic.cities, cityPosition, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            cityPosition = position;
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
                    //AlertDialog.Builder builder = alertDialog;
                    alertDialog.setTitle("Üniversiteler");
                    alertDialog.setSingleChoiceItems(Basic.universities, universityPosition, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                           universityPosition = position;
                        }
                    });
                    alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
                    edittext.setText(details);
                    builder.setView(edittext);
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            details = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }

            }
        });
        Button btnEkle = (Button)findViewById(R.id.btnEkle);
        btnEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject params = null, func = null;
                try {
                    params = new JSONObject();
                    params.put("email",AnaActivity.getEmail());
                    params.put("login_token",AnaActivity.getLogin_token());
                    params.put("title",title);
                    params.put("price",price);
                    params.put("cityPosition",cityPosition);
                    params.put("universityPosition",universityPosition);
                    params.put("details",details);
                    func = new JSONObject();
                    func.put("method_name", "addAdvert");
                    func.put("method_params",params);
                } catch (JSONException ex) {

                }

                RestFul restful = new RestFul();
                String JSONResponse = restful.JSONRequest(func);
                AddAdvert advert = new AddAdvert(JSONResponse);

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
