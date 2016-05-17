package com.utm.miragessee.universiteticaretmerkezi;

import android.app.TabActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;

@SuppressWarnings("deprecation")
public class ProfileActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TabHost tabHost = (TabHost) findViewById(android.R.id.tabhost);

        TabHost.TabSpec tab1 = tabHost.newTabSpec("Profilim");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Mesajlarım");

        tab1.setIndicator("Profilim");
        //tab1.setIndicator("Profilim", getResources().getDrawable(R.drawable.common_signin_btn_icon_pressed_light));
        tab1.setContent(new Intent(this, DetailActivity.class));

        tab2.setIndicator("Mesajlarım");
        //tab2.setIndicator("Mesajlarım", getResources().getDrawable(R.drawable.base_drawer_message));
        tab2.setContent(new Intent(this, PmActivity.class));

        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_profile, menu);
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
