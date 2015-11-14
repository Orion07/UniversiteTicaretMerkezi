package com.utm.miragessee.universiteticaretmerkezi;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class AnaActivity extends AppCompatActivity {

    private String[] mPlanetTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    ListView list;
    ListView listView;

    String[] web = {
            "Kitaplar",
            "Kalemler",
            "Defterler",
            "Apartlar",
            "Daireler",
            "Evcil Hayvan",
            "Bilgisayarlar"
    } ;

    Integer[] imageId = {
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name,
            R.drawable.ic_action_name
    };

    String[] kitaplar = {
            "80 Günde Devri Alem 10 TL",
            "Sefiller 50 TL",
            "LOTR SERİSİ 100 TL",
            "HP SERİSİ 100 TL",
            "İnsan Ne İle Yaşar 10 TL",
            "Kemancı 10 TL",
            "Android Programlama Kitabı 30 TL",
            "80 Günde Devri Alem 10 TL",
            "Sefiller 50 TL",
            "LOTR SERİSİ 100 TL",
            "HP SERİSİ 100 TL",
            "İnsan Ne İle Yaşar 10 TL",
            "Kemancı 10 TL",
            "Android Programlama Kitabı 30 TL",
            "80 Günde Devri Alem 10 TL",
            "Sefiller 50 TL",
            "LOTR SERİSİ 100 TL",
            "HP SERİSİ 100 TL",
            "İnsan Ne İle Yaşar 10 TL",
            "Kemancı 10 TL",
            "Android Programlama Kitabı 30 TL",
            "80 Günde Devri Alem 10 TL",
            "Sefiller 50 TL",
            "LOTR SERİSİ 100 TL",
            "HP SERİSİ 100 TL",
            "İnsan Ne İle Yaşar 10 TL",
            "Kemancı 10 TL",
            "Android Programlama Kitabı 30 TL",
            "80 Günde Devri Alem 10 TL",
            "Sefiller 50 TL",
            "LOTR SERİSİ 100 TL",
            "HP SERİSİ 100 TL",
            "İnsan Ne İle Yaşar 10 TL",
            "Kemancı 10 TL",
            "Android Programlama Kitabı 30 TL",
            "80 Günde Devri Alem 10 TL",
            "Sefiller 50 TL",
            "LOTR SERİSİ 100 TL",
            "HP SERİSİ 100 TL",
            "İnsan Ne İle Yaşar 10 TL",
            "Kemancı 10 TL",
            "Android Programlama Kitabı 30 TL"
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana);

        listView = (ListView) findViewById(R.id.listView);

        mPlanetTitles = web;
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<String>(this,
                R.layout.drawer_list_item, mPlanetTitles));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        final CustomList adapter = new
                CustomList(AnaActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.left_drawer);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(AnaActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();
                if (web[position].equals("Kitaplar")) {
                    CustomList2 kitap = new
                            CustomList2(AnaActivity.this, kitaplar, imageId);
                    listView.setAdapter(kitap);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Toast.makeText(AnaActivity.this, "You Clicked at " + kitaplar[+position], Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {
                    ListAdapter bos = null;
                    listView.setAdapter(bos);
                }
                selectItem(position);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                //R.drawable.ic_drawer,  /* nav drawer icon to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                //getActionBar().setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                //getActionBar().setTitle(mTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_ana, menu);
        return true;
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void selectItem(int position) {
        Toast.makeText(this, R.string.app_name, Toast.LENGTH_SHORT).show();

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(mPlanetTitles[position]);
        mDrawerLayout.closeDrawer(mDrawerList);

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }
}
