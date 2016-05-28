package com.utm.miragessee.universiteticaretmerkezi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Functions.Basic;
import Functions.IRestfulTask;
import Functions.RestFul;
import JsonParser.Advert;
import JsonParser.ElementManager;

public class IlanActivity extends AppCompatActivity implements IRestfulTask{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan);
        getAdvert();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ilan, menu);
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
    public void getAdvert()
    {
        Bundle bundle = getIntent().getExtras();
        int advertid = bundle.getInt("advertid");
        JSONObject obj = null,func = null;
        try
        {
            obj = new JSONObject();
            obj.put("email",AnaActivity.getEmail());
            obj.put("login_token",AnaActivity.getLogin_token());
            obj.put("advert_id",advertid);
            func = new JSONObject();
            func.put("method_name","getAdvert");
            func.put("method_params",obj);
            RestfulTask task = new RestfulTask();
            task.execute(func);
        }catch (Exception ex)
        {
            Log.d("GET ADVERT : ", ex.getMessage());
        }
    }
    public void sendMessage(int userid,int advertid,String message)
    {
        JSONObject params = null, func = null;
        Basic b = new Basic();
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token", AnaActivity.getLogin_token());
            params.put("user_id", userid);
            params.put("advert_id",advertid);
            params.put("message", message);
            func = new JSONObject();
            func.put("method_params", params);
            func.put("method_name", "addMessage");
            RestFul restFul = new RestFul();
            String str = restFul.JSONRequest(func);
            JSONObject obj = new JSONObject(str);
            if (obj.has("AddMessage")) {
                JSONObject r = obj.getJSONObject("addMessage");
                int result = r.getInt("result");
                if (result == 1) {
                    b.MsgBox(IlanActivity.this, "Mesajınız Gönderildi");
                } else {
                    b.MsgBox(IlanActivity.this,"Mesajı gönderirken bir hata oluştu");
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public class GalleryAdapter extends BaseAdapter
    {

        private Context mContext;
        private ArrayList<String> photoList = null;
        public GalleryAdapter(Context context,ArrayList<String> photoList)
        {
            this.mContext = context;
            this.photoList = photoList;
        }
        @Override
        public int getCount() {
            return photoList.size();
        }

        @Override
        public Object getItem(int position) {
            return photoList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            System.out.println("POSITION : " + position);
            ImageView i = new ImageView(mContext);
            Basic b = new Basic();
            Bitmap map = b.decompressImage(photoList.get(position));
            i.setImageBitmap(map);
            i.setLayoutParams(new Gallery.LayoutParams(map.getHeight(), map.getWidth()));
            //i.setScaleType(ImageView.ScaleType.FIT_XY);
            return i;
        }
    }
    @Override
    public void postResult(String s)
    {
        final Advert advert = new Advert(s);
        Gallery gallery =(Gallery)findViewById(R.id.gallery);
        GalleryAdapter galleryObj = new GalleryAdapter(this,advert.getPhotoList());
        gallery.setSpacing(1);
        gallery.setAdapter(galleryObj);

        TextView title = (TextView)findViewById(R.id.textView12);
        title.setText(advert.getTitle());

        TextView price = (TextView)findViewById(R.id.textView13);
        price.setText(String.valueOf(advert.getPrice()));

        TextView date = (TextView)findViewById(R.id.textView14);
        date.setText(advert.getDate());

        TextView city = (TextView)findViewById(R.id.textView15);
        city.setText(Basic.cities[advert.getCityPosition()]);

        TextView name = (TextView)findViewById(R.id.textView16);
        name.setText(advert.getFirstname() + " " + advert.getLastname());

        TextView msg = (TextView)findViewById(R.id.textView17);
        msg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IlanActivity.this);
                final EditText txt = new EditText(IlanActivity.this);
                builder.setView(txt);
                builder.setPositiveButton("Gönder", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String message = txt.getText().toString();
                        Basic b = new Basic();
                        if (message.length() > 0) {
                            sendMessage(advert.getUserId(),advert.getAdvertId(),message);
                        } else {
                            b.MsgBox(IlanActivity.this, "Boş Mesaj Gönderemezsiniz");
                        }
                    }
                });
                builder.show();
            }
        });

        TextView university = (TextView)findViewById(R.id.textView19);
        university.setText(Basic.universities[advert.getUniversityPosition()]);

        Button makecall = (Button)findViewById(R.id.button3);
        makecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent call = new Intent(Intent.ACTION_CALL);
                String tel = advert.getPhone().replace("(", "(0");
                Log.d("TELLLLL : ", tel);
                call.setData(Uri.parse("tel:" + tel));
                startActivity(call);
            }
        });

        Button btn = (Button)findViewById(R.id.button4);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(IlanActivity.this);
                TextView txt = new TextView(IlanActivity.this);
                txt.setSingleLine(false);
                txt.setGravity(Gravity.LEFT | Gravity.TOP);
                txt.setInputType(EditorInfo.TYPE_TEXT_FLAG_MULTI_LINE);
                txt.setTextColor(Color.BLACK);
                txt.setText(advert.getDetails());
                txt.setMovementMethod(new ScrollingMovementMethod());
                builder.setView(txt);
                builder.setPositiveButton("Tamam", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
            }
        });

    }
    public class RestfulTask extends AsyncTask<JSONObject,Void,String>
    {
        public String restfulURL = getString(R.string.restfulURL);
        public IRestfulTask delegate = null;
        private ProgressDialog pdia;
        @Override
        protected String doInBackground(JSONObject... params) {
            HttpClient httpClient = new DefaultHttpClient();
            JSONObject json = params[0];
            try {
                HttpPost request = new HttpPost(restfulURL);
                System.out.println("JSON DATA2 : " + json.toString());
                StringEntity entity = new StringEntity(json.toString());
                request.addHeader("content-type", "application/x-www-form-urlencoded");
                request.setEntity(entity);
                HttpResponse response = httpClient.execute(request);
                System.out.println("Status Code : " + response.getStatusLine());
                BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer sb = new StringBuffer("");
                String line = "";
                String NL = System.getProperty("line.separator");
                while ((line = rd.readLine()) != null) {
                    sb.append(line + NL);
                }
                rd.close();
                return sb.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return "";
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdia = new ProgressDialog(IlanActivity.this);
            pdia.setMessage("İlan Yükleniyor...");
            pdia.show();
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            postResult(result);
            pdia.dismiss();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onCancelled(String result) {
            super.onCancelled(result);
        }
    }
}
