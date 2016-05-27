package com.utm.miragessee.universiteticaretmerkezi;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.location.internal.LocationRequestUpdateData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import Functions.Basic;
import Functions.RestFul;
import JsonParser.AddAdvert;
import JsonParser.Signup;

public class IlanEkleActivity extends AppCompatActivity {

    ListView list;
    String title = null;
    int price = 0;
    int cityPosition = -1;
    int fotoPosition = -1;
    int universityPosition = -1;
    int category_id = -1;
    String details = null;
    ArrayList<String> photoList = null;
    //String[] foto = {"Fotoğraf Çek","Galeriden Fotoğraf Seç"};
    String[] web = {
            "Kategoriler",
            "İlan Başlığı",
            "Fiyat",
            "İl",
            "Üniversite",
            "Açıklama",
            "Fotoğraf"
    } ;
    private final int UTM_CODE = 618;
    //ImageView imgTakenPhoto;
    //private static final int CAM_REQUEST = 1313;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan_ekle);
        final JSONObject params = new JSONObject(), func = new JSONObject();
        CustomList adapter = new CustomList(IlanEkleActivity.this, web);
        //imgTakenPhoto = (ImageView) findViewById(R.id.imageView3);
        list=(ListView)findViewById(R.id.listView3);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if (position == 0) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Kategoriler");
                    builder.setSingleChoiceItems(Basic.categories, category_id, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            category_id = position + 1;
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alertDialog.show();
                }
                if (position == 1) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("İlan Başlığı");
                    final EditText edittext = new EditText(getApplicationContext());
                    edittext.setText(title);
                    edittext.setTextColor(Color.BLUE);
                    builder.setView(edittext);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            title = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }
                if (position == 2) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Fiyat");
                    final EditText edittext = new EditText(getApplicationContext());
                    edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
                    edittext.setText(String.valueOf(price));
                    edittext.setTextColor(Color.BLUE);
                    builder.setView(edittext);
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            price = Integer.parseInt(edittext.getText().toString());
                        }
                    });
                    alertDialog.show();
                }
                if (position == 3) {
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
                if (position == 4) {
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
                if (position == 5) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Açıklama");
                    final EditText edittext = new EditText(getApplicationContext());
                    edittext.setText(details);
                    edittext.setTextColor(Color.BLUE);
                    builder.setView(edittext);

                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            details = edittext.getText().toString();
                        }
                    });
                    alertDialog.show();
                }
                if (position == 6) {
                    /*
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(IlanEkleActivity.this);
                    AlertDialog.Builder builder = alertDialog;
                    builder.setTitle("Fotoğraf");
                    builder.setSingleChoiceItems(foto, fotoPosition, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int position) {
                            fotoPosition = position;
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Toast.makeText(IlanEkleActivity.this, ""+fotoPosition, Toast.LENGTH_LONG).show();
                            if (fotoPosition == 0) {
                                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                startActivityForResult(cameraIntent, CAM_REQUEST);
                            }
                        }
                    });
                    alertDialog.show();
                    */
                    Intent fotoEkleActivity = new Intent(getBaseContext(), FotoEkleActivity.class);
                    //startActivity(fotoEkleActivity);
                    startActivityForResult(fotoEkleActivity,UTM_CODE);
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
                    params.put("category_id",category_id);
                    params.put("cityPosition",cityPosition);
                    params.put("universityPosition",universityPosition);
                    params.put("details",details);
                    ArrayList<String> list = imageList(photoList);
                    if(list == null)
                    {
                        Log.d("Photo List state : ","list is null");
                        return ;
                    }
                    JSONArray array = new JSONArray(list);
                    params.put("photos",array);
                    func = new JSONObject();
                    func.put("method_name", "addAdvert");
                    func.put("method_params", params);
                } catch (JSONException ex) {

                }

                RestFul restful = new RestFul();
                Basic b = new Basic();
                String JSONResponse = restful.JSONRequest(func);
                Log.i("REST : ",JSONResponse);
                AddAdvert advert = new AddAdvert(JSONResponse);
                b.MsgBox(getApplicationContext(),advert.getMessage());
                if(advert.getResult() == 1) {
                    finish();
                }
            }
        });
    }
    public ArrayList<String> imageList(ArrayList<String> list)
    {
        ArrayList<String> photos = new ArrayList<String>();
        Basic b = new Basic();
        if(list!=null) {

            Iterator iterator = list.iterator();
            while (iterator.hasNext()) {
                String ss = (String) iterator.next();
                photos.add(b.compressImage(ss));
            }
        }
        return photos;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == UTM_CODE) {
            if (resultCode == RESULT_OK) {
                Bundle extras = data.getExtras();
                photoList = extras.getStringArrayList("photoList");
                Log.d("Activity Test","Geri döndüm qnq,Extra : " + photoList.toString() );
            }
        }
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
