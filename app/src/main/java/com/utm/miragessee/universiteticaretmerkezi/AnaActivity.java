package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import Fragments.ShowListFragment;
import Functions.Basic;
import JsonParser.Categories;
import JsonParser.CategoryManager;
import JsonParser.Elements;


public class AnaActivity extends AppCompatActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, ShowListFragment.OnFragmentInteractionListener {
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;


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

        /*Bundle b = getIntent().getExtras();
        String value = b.getString("email");

        TextView email = (TextView)findViewById(R.id.txtMyEmail);
        email.setText(value);*/
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        if(position == 0) {
            //profile activity
        }
        else {
            Elements e = new Elements(position);
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, ShowListFragment.newInstance(e.getElementsList()))
                    .commit();

            Categories c = new Categories();
            ArrayList<CategoryManager> manager = c.getCategoriesList();
            CategoryManager catManager = manager.get(position - 1);
            if(catManager != null)
                setFragmenTitle(catManager.getCateName());
        }
    }
    public void setFragmenTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        Basic b = new Basic();
        int id = item.getItemId();
        /*if (id == R.id.search) {
            return true;
        }*/
        switch (id)
        {
            case R.id.search:
                b.MsgBox(getApplicationContext(),"ara basildi");
                return true;
            case R.id.ilanekle:
                b.MsgBox(getApplicationContext(),"ilan ekle basildi");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
