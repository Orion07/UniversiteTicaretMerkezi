package com.utm.miragessee.universiteticaretmerkezi;

import android.content.Context;
import android.graphics.Bitmap;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import Functions.Basic;
import JsonParser.ElementManager;

public class IlanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ilan);
        Bundle bundle = getIntent().getExtras();
        ElementManager element = (ElementManager)bundle.getSerializable("advert");
        System.out.println(element.getBaslik());
        TextView txtTitle = (TextView)findViewById(R.id.textView2);
        txtTitle.setText(element.getBaslik());
        Gallery gallery =(Gallery)findViewById(R.id.gallery);
        GalleryAdapter galleryObj = new GalleryAdapter(this,element.getResim());
        gallery.setSpacing(1);
        gallery.setAdapter(galleryObj);
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
    public class GalleryAdapter extends BaseAdapter
    {

        private Context mContext;
        String images = null;
        public GalleryAdapter(Context context,String strImage)
        {
            this.mContext = context;
            this.images = strImage;
            System.out.println("IMAGE : " + strImage);
        }
        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public Object getItem(int position) {
            return null;
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
            Bitmap map = b.decompressImage(images);
            i.setImageBitmap(map);
            i.setLayoutParams(new Gallery.LayoutParams(map.getHeight(),map.getWidth()));
            i.setScaleType(ImageView.ScaleType.FIT_XY);
            return i;
        }
    }
}
