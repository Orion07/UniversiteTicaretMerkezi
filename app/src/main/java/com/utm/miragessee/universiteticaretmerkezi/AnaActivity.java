package com.utm.miragessee.universiteticaretmerkezi;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import Fragments.ShowListFragment;
import Functions.Basic;
import Functions.IRestfulTask;
import JsonParser.ElementManager;
import JsonParser.Elements;
import JsonParser.GetCategoryList;


public class AnaActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ShowListFragment.OnFragmentInteractionListener ,IRestfulTask{
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;
    private static String email = "";
    private static String login_token = "";
    private Elements e;
    public static String getEmail() {
        return email;
    }
    public boolean firsttime = false;

    public static String getLogin_token() {
        return login_token;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();


        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

        TextView profil = (TextView) findViewById(R.id.textView);
        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profileActivity = new Intent(AnaActivity.this, ProfileActivity.class);
                startActivity(profileActivity);
            }
        });
        Bundle b = getIntent().getExtras();
        email = b.getString("email");
        login_token = b.getString("login_token");
        getCategory(0);
    }
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        getCategory(position);
    }
    public void setFragmenTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);

    }
    public void getCategory(int position)
    {
        Log.i("List Index : ", String.valueOf(position));
        Log.d("Email : ",email);
        if(firsttime) {
            e = new Elements();
            RestfulTask task = new RestfulTask();
            task.execute(e.getList(position));
            setFragmenTitle(Basic.categories[position]);
        }
        firsttime = true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.ana, menu);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onBackPressed() {
        // your code.
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Basic b = new Basic();
        int id = item.getItemId();
        switch (id)
        {
            case R.id.search:
                b.MsgBox(getApplicationContext(),"ara basildi");
                Intent ilanAraActivity = new Intent(getBaseContext(), IlanAraActivity.class);
                startActivity(ilanAraActivity);
                return true;
            case R.id.ilanekle:
                b.MsgBox(getApplicationContext(),"ilan ekle basildi");
                Intent ilanEkleActivity = new Intent(getBaseContext(), IlanEkleActivity.class);
                startActivity(ilanEkleActivity);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }
    @Override
    public void postResult(String s)
    {
        System.out.println("DATA GELDİ QNQ : " + s);
        GetCategoryList catList = new GetCategoryList(s);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, ShowListFragment.newInstance(catList.list()))
                .commit();
        //elementsList = catList.list();
        //ArrayAdapter<ElementManager> adapter = new ElemanlarManagerListAdapter();
        //listView2.setAdapter(adapter);
        //swipeLayout.setRefreshing(false);

        //FragmentManager fragmentManager = getSupportFragmentManager();
        //fragmentManager.beginTransaction()
        //        .replace(R.id.container, ShowListFragment.newInstance(catList.list()))
        //        .commit();
    }

    public class RestfulTask extends AsyncTask<JSONObject,Void,String>
    {
        public String restfulURL = getString(R.string.restfulURL);
        public IRestfulTask delegate = null;
        private ProgressDialog pdia;
        @Override
        protected String doInBackground(JSONObject... params) {

            HttpClient httpClient = new DefaultHttpClient();
            try {
                JSONObject json = params[0];
                HttpPost request = new HttpPost(restfulURL);
                System.out.println("JSON DATA2 : " + json.toString());
                StringEntity entity = new StringEntity(json.toString(),"UTF-8");
                entity.setContentType("application/json");
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
            pdia = new ProgressDialog(AnaActivity.this);
            pdia.setMessage("Loading...");
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
