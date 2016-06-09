package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Functions.Basic;
import Functions.IRestfulTask;
import Functions.RestFul;
import JsonParser.ElementManager;

public class IlanlarimActivity extends Activity implements IRestfulTask{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        getMyAdverts();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ilanlarim, menu);
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
    public void getMyAdverts()
    {
        JSONObject params = null,func = null;
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token", AnaActivity.getLogin_token());
            func = new JSONObject();
            func.put("method_params", params);
            func.put("method_name", "getMyAdverts");
            RestfulTask task = new RestfulTask();
            task.execute(func);
        }catch (Exception ex){
           ex.printStackTrace();
        }
    }
    public ArrayList<ElementManager> parseJSON(String jsonStr)
    {
        ArrayList<ElementManager> elementsList = new ArrayList<ElementManager>();
        try {
            JSONObject json = new JSONObject(jsonStr);
            if(json.has("getMyAdverts")) {
                JSONObject jsonCategory = (JSONObject) json.get("getMyAdverts");
                int result = jsonCategory.getInt("result");
                if(result == 1) {
                    JSONArray jsonArray = jsonCategory.getJSONArray("list");
                    for(int i = 0;i<jsonArray.length();i++)
                    {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        int advertid = obj.getInt("advert_id");
                        String photo = obj.getString("photodata");
                        String title = obj.getString("title");
                        String city = Basic.cities[obj.getInt("cityPosition")];
                        String price = String.valueOf(obj.getInt("price"));
                        ElementManager element = new ElementManager(advertid,photo,title,city,price);
                        elementsList.add(element);
                    }
                }

            }else
            {
                Log.i("JSON ILANLARIM : ", "Ilanlarimi çekerken hata oluştu");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return elementsList;
    }
    public void deleteAdvert(int advertid)
    {
        Basic b = new Basic();
        JSONObject params = null,func = null;
        try {
            params = new JSONObject();
            params.put("email", AnaActivity.getEmail());
            params.put("login_token", AnaActivity.getLogin_token());
            params.put("advert_id",advertid);
            func = new JSONObject();
            func.put("method_params", params);
            func.put("method_name", "deleteAdvert");
            RestFul restFul = new RestFul();
            String strJson = restFul.JSONRequest(func);
            JSONObject obj = new JSONObject(strJson);
            if (obj.has("deleteAdvert")) {
                JSONObject r = obj.getJSONObject("deleteAdvert");
                int result = r.getInt("result");
                if (result == 1) {
                    b.MsgBox(getApplicationContext(), "İlan Silindi");
                } else {
                    b.MsgBox(getApplicationContext(), "İlanı silerken bir hata oluştu");
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
    @Override
    public void postResult(String s)
    {

        ArrayList<ElementManager> list = parseJSON(s);
        Log.d("Ilanlar : ", list.toString());
        ElementAdapter adapter = new ElementAdapter(list);
        ListView listView = (ListView)findViewById(R.id.listView7);
        listView.setAdapter(adapter);
    }

    private class ElementAdapter extends ArrayAdapter<ElementManager> {
        ArrayList<ElementManager> elementsList = null;
        public ElementAdapter(ArrayList<ElementManager> elementsList) {
            super(IlanlarimActivity.this, R.layout.list_single, elementsList);
            this.elementsList = elementsList;
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.list_single, null, false);

            final ElementManager currentElement = elementsList.get(position);
            final Basic b = new Basic();
            TableRow tableRow = (TableRow) view.findViewById(R.id.tablerow);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("advertid",currentElement.getId());
                    Intent detail = new Intent(IlanlarimActivity.this, IlanActivity.class);
                    detail.putExtras(bundle);
                    startActivity(detail);
                }
            });
            tableRow.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Log.d("Table Row 2 : ", "Tabloya basilma durduruldu");
                    AlertDialog.Builder builder = new AlertDialog.Builder(IlanlarimActivity.this);
                    ListView list = new ListView(IlanlarimActivity.this);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(IlanlarimActivity.this, android.R.layout.simple_list_item_1, android.R.id.text1, new String[]{"Sil","Düzenle"});
                    list.setAdapter(adapter);
                    builder.setView(list);
                    final AlertDialog dialog = builder.create();
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            switch (position) {
                                case 0:
                                    deleteAdvert(currentElement.getId());
                                    elementsList.remove(currentElement);
                                    break;
                                case 1:
                                    b.MsgBox(getApplicationContext(), "Düzenle sonra eklenecektir.");
                                    break;
                            }
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                    v.setPressed(false);
                    return false;
                }
            });
            TextView section = (TextView) view.findViewById(R.id.section);
            TextView location = (TextView) view.findViewById(R.id.location);
            TextView price = (TextView) view.findViewById(R.id.price);
            section.setText(currentElement.getBaslik());
            location.setText(currentElement.getKonum());
            price.setText(currentElement.getFiyat());
            ImageView img = (ImageView) view.findViewById(R.id.img);
            Bitmap map = b.decompressImage(currentElement.getResim());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageBitmap(map);
            return view;
        }
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
            pdia = new ProgressDialog(IlanlarimActivity.this);
            pdia.setMessage("İlanlar Yükleniyor...");
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
