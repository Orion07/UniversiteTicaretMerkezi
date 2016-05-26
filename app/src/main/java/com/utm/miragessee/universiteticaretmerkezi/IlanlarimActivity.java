package com.utm.miragessee.universiteticaretmerkezi;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import Functions.Basic;
import JsonParser.ElementManager;

public class IlanlarimActivity extends Activity {
    ArrayList<ElementManager> elementsList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilanlarim);
        elementsList = new ArrayList<ElementManager>();

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
    private class ElemanlarManagerListAdapter extends ArrayAdapter<ElementManager> {
        public ElemanlarManagerListAdapter() {
            super(IlanlarimActivity.this, R.layout.list_single, elementsList);
        }

        @Override
        public View getView(final int position, View view, ViewGroup parent) {
            if (view == null)
                view = getLayoutInflater().inflate(R.layout.list_single, null, false);

            final ElementManager currentElement = elementsList.get(position);
            TableRow tableRow = (TableRow) view.findViewById(R.id.tablerow);
            tableRow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Log.i("currentElement : ",String.valueOf(position));
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("advert",currentElement);
                    Intent detail = new Intent(IlanlarimActivity.this, IlanActivity.class);
                    detail.putExtras(bundle);
                    startActivity(detail);
                }
            });
            TextView section = (TextView) view.findViewById(R.id.section);
            TextView location = (TextView) view.findViewById(R.id.location);
            TextView price = (TextView) view.findViewById(R.id.price);
            section.setText(currentElement.getBaslik());
            location.setText(currentElement.getKonum());
            price.setText(currentElement.getFiyat());
            ImageView img = (ImageView) view.findViewById(R.id.img);
            Basic b = new Basic();
            Bitmap map = b.decompressImage(currentElement.getResim());
            img.setScaleType(ImageView.ScaleType.FIT_XY);
            img.setImageBitmap(map);
            return view;
        }
    }
}
